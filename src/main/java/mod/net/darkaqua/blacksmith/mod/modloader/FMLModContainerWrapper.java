package net.darkaqua.blacksmith.mod.modloader;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.common.collect.*;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.asm.transformers.BlamingTransformer;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.discovery.ModCandidate;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLEvent;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.versioning.ArtifactVersion;
import net.minecraftforge.fml.common.versioning.DefaultArtifactVersion;
import net.minecraftforge.fml.common.versioning.VersionRange;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.io.FileInputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.security.cert.Certificate;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by cout970 on 06/02/2016.
 */
public class FMLModContainerWrapper extends FMLModContainer {

    public Object modInstance;
    public File source;
    public ModMetadata modMetadata;
    public String className;
    public Map<String, Object> descriptor;
    public boolean enabled = true;
    public String internalVersion;
    public boolean overridesMetadata;
    public EventBus eventBus;
    public LoadController controller;
    public DefaultArtifactVersion processedVersion;

    public String annotationDependencies;
    public VersionRange minecraftAccepted;
    public boolean fingerprintNotPresent;
    public Set<String> sourceFingerprints;
    public Certificate certificate;
    public String modLanguage;
    public ILanguageAdapter languageAdapter;
    public Disableable disableability;
    public ListMultimap<Class<? extends FMLEvent>, Method> eventMethods;
    public Map<String, String> customModProperties;
    public ModCandidate candidate;
    public URL updateJSONUrl;

    public FMLModContainerWrapper(String className, ModCandidate container, Map<String, Object> modDescriptor) {
        super(className, container, modDescriptor);
        this.className = className;
        this.source = container.getModContainer();
        this.candidate = container;
        this.descriptor = modDescriptor;
        this.eventMethods = ArrayListMultimap.create();

        this.modLanguage = (String) modDescriptor.get("modLanguage");
        String languageAdapterType = (String) modDescriptor.get("modLanguageAdapter");
        if (Strings.isNullOrEmpty(languageAdapterType)) {
            this.languageAdapter = "scala".equals(modLanguage) ? new ILanguageAdapter.ScalaAdapter() : new ILanguageAdapter.JavaAdapter();
        } else {
            // Delay loading of the adapter until the mod is on the classpath, in case the mod itself contains it.
            this.languageAdapter = null;
            FMLLog.finer("Using custom language adapter %s for %s (modid: %s)", languageAdapterType, this.className, getModId());
        }
    }

    private ILanguageAdapter getLanguageAdapter() {
        if (languageAdapter == null) {
            try {
                languageAdapter = (ILanguageAdapter) Class.forName((String) descriptor.get("modLanguageAdapter"), true, Loader.instance().getModClassLoader()).newInstance();
            } catch (Exception ex) {
                FMLLog.log(Level.ERROR, ex, "Error constructing custom mod language adapter referenced by %s (modid: %s)", this.className, getModId());
                throw new RuntimeException(ex);
            }
        }
        return languageAdapter;
    }

    @Override
    public String getModId() {
        return (String) descriptor.get("modid");
    }

    @Override
    public String getName() {
        return modMetadata.name;
    }

    @Override
    public String getVersion() {
        return internalVersion;
    }

    @Override
    public File getSource() {
        return source;
    }

    @Override
    public ModMetadata getMetadata() {
        return modMetadata;
    }

    public Properties searchForVersionProperties() {
        try {
            FMLLog.log(getModId(), Level.DEBUG, "Attempting to load the file version.properties from %s to locate a version number for %s", getSource().getName(), getModId());
            Properties version = null;
            if (getSource().isFile()) {
                ZipFile source = new ZipFile(getSource());
                ZipEntry versionFile = source.getEntry("version.properties");
                if (versionFile != null) {
                    version = new Properties();
                    version.load(source.getInputStream(versionFile));
                }
                source.close();
            } else if (getSource().isDirectory()) {
                File propsFile = new File(getSource(), "version.properties");
                if (propsFile.exists() && propsFile.isFile()) {
                    version = new Properties();
                    FileInputStream fis = new FileInputStream(propsFile);
                    version.load(fis);
                    fis.close();
                }
            }
            return version;
        } catch (Exception e) {
            Throwables.propagateIfPossible(e);
            FMLLog.log(getModId(), Level.TRACE, "Failed to find a usable version.properties file");
            return null;
        }
    }

    @Override
    public void setEnabledState(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Set<ArtifactVersion> getRequirements() {
        return modMetadata.requiredMods;
    }

    @Override
    public List<ArtifactVersion> getDependencies() {
        return modMetadata.dependencies;
    }

    @Override
    public List<ArtifactVersion> getDependants() {
        return modMetadata.dependants;
    }

    @Override
    public String getSortingRules() {
        return ((overridesMetadata || !modMetadata.useDependencyInformation) ? Strings.nullToEmpty(annotationDependencies) : modMetadata.printableSortingRules());
    }

    @Override
    public boolean matches(Object mod) {
        return mod == modInstance;
    }

    @Override
    public Object getMod() {
        return modInstance;
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        if (this.enabled) {
            FMLLog.log(getModId(), Level.DEBUG, "Enabling mod %s", getModId());
            this.eventBus = bus;
            this.controller = controller;
            eventBus.register(this);
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    private Method gatherAnnotations(Class<?> clazz) throws Exception {
        Method factoryMethod = null;
        for (Method m : clazz.getDeclaredMethods()) {
            for (Annotation a : m.getAnnotations()) {
                if (a.annotationType().equals(Mod.EventHandler.class)) {
                    if (m.getParameterTypes().length == 1 && FMLEvent.class.isAssignableFrom(m.getParameterTypes()[0])) {
                        m.setAccessible(true);
                        eventMethods.put((Class<? extends FMLEvent>) m.getParameterTypes()[0], m);
                    } else {
                        FMLLog.log(getModId(), Level.ERROR, "The mod %s appears to have an invalid event annotation %s. This annotation can only apply to methods with recognized event arguments - it will not be called", getModId(), a.annotationType().getSimpleName());
                    }
                } else if (a.annotationType().equals(Mod.InstanceFactory.class)) {
                    if (Modifier.isStatic(m.getModifiers()) && m.getParameterTypes().length == 0 && factoryMethod == null) {
                        m.setAccessible(true);
                        factoryMethod = m;
                    } else if (!(Modifier.isStatic(m.getModifiers()) && m.getParameterTypes().length == 0)) {
                        FMLLog.log(getModId(), Level.ERROR, "The InstanceFactory annotation can only apply to a static method, taking zero arguments - it will be ignored on %s(%s)", m.getName(), Arrays.asList(m.getParameterTypes()));
                    } else if (factoryMethod != null) {
                        FMLLog.log(getModId(), Level.ERROR, "The InstanceFactory annotation can only be used once, the application to %s(%s) will be ignored", m.getName(), Arrays.asList(m.getParameterTypes()));
                    }
                }
            }
        }
        return factoryMethod;
    }

    private void processFieldAnnotations(ASMDataTable asmDataTable) throws Exception {
        SetMultimap<String, ASMDataTable.ASMData> annotations = asmDataTable.getAnnotationsFor(this);

        parseSimpleFieldAnnotation(annotations, Mod.Instance.class.getName(), new Function<ModContainer, Object>() {
            @Override
            public Object apply(ModContainer mc) {
                return mc.getMod();
            }
        });
        parseSimpleFieldAnnotation(annotations, Mod.Metadata.class.getName(), new Function<ModContainer, Object>() {
            @Override
            public Object apply(ModContainer mc) {
                return mc.getMetadata();
            }
        });
    }

    private void parseSimpleFieldAnnotation(SetMultimap<String, ASMDataTable.ASMData> annotations, String annotationClassName, Function<ModContainer, Object> retreiver) throws IllegalAccessException {
        String[] annName = annotationClassName.split("\\.");
        String annotationName = annName[annName.length - 1];
        for (ASMDataTable.ASMData targets : annotations.get(annotationClassName)) {
            String targetMod = (String) targets.getAnnotationInfo().get("value");
            Field f = null;
            Object injectedMod = null;
            ModContainer mc = this;
            boolean isStatic = false;
            Class<?> clz = modInstance.getClass();
            if (!Strings.isNullOrEmpty(targetMod)) {
                if (Loader.isModLoaded(targetMod)) {
                    mc = Loader.instance().getIndexedModList().get(targetMod);
                } else {
                    mc = null;
                }
            }
            if (mc != null) {
                try {
                    clz = Class.forName(targets.getClassName(), true, Loader.instance().getModClassLoader());
                    f = clz.getDeclaredField(targets.getObjectName());
                    f.setAccessible(true);
                    isStatic = Modifier.isStatic(f.getModifiers());
                    injectedMod = retreiver.apply(mc);
                } catch (Exception e) {
                    Throwables.propagateIfPossible(e);
                    FMLLog.log(getModId(), Level.WARN, e, "Attempting to load @%s in class %s for %s and failing", annotationName, targets.getClassName(), mc.getModId());
                }
            }
            if (f != null) {
                Object target = null;
                if (!isStatic) {
                    target = modInstance;
                    if (!modInstance.getClass().equals(clz)) {
                        FMLLog.log(getModId(), Level.WARN, "Unable to inject @%s in non-static field %s.%s for %s as it is NOT the primary mod instance", annotationName, targets.getClassName(), targets.getObjectName(), mc.getModId());
                        continue;
                    }
                }
                f.set(target, injectedMod);
            }
        }
    }

    @Subscribe
    public void constructMod(FMLConstructionEvent event) {
        try {
            BlamingTransformer.addClasses(getModId(), candidate.getClassList());
            ModClassLoader modClassLoader = event.getModClassLoader();
            modClassLoader.addFile(source);
            modClassLoader.clearNegativeCacheFor(candidate.getClassList());
            Class<?> clazz = Class.forName(className, true, modClassLoader);

            Certificate[] certificates = clazz.getProtectionDomain().getCodeSource().getCertificates();
            int len = 0;
            if (certificates != null) {
                len = certificates.length;
            }
            ImmutableList.Builder<String> certBuilder = ImmutableList.<String>builder();
            for (int i = 0; i < len; i++) {
                certBuilder.add(CertificateHelper.getFingerprint(certificates[i]));
            }

            ImmutableList<String> certList = certBuilder.build();
            sourceFingerprints = ImmutableSet.copyOf(certList);

            String expectedFingerprint = (String) descriptor.get("certificateFingerprint");

            fingerprintNotPresent = true;

            if (expectedFingerprint != null && !expectedFingerprint.isEmpty()) {
                if (!sourceFingerprints.contains(expectedFingerprint)) {
                    Level warnLevel = Level.ERROR;
                    if (source.isDirectory()) {
                        warnLevel = Level.TRACE;
                    }
                    FMLLog.log(getModId(), warnLevel, "The mod %s is expecting signature %s for source %s, however there is no signature matching that description", getModId(), expectedFingerprint, source.getName());
                } else {
                    certificate = certificates[certList.indexOf(expectedFingerprint)];
                    fingerprintNotPresent = false;
                }
            }

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> props = (List<Map<String, Object>>) descriptor.get("customProperties");
            if (props != null) {
                com.google.common.collect.ImmutableMap.Builder<String, String> builder = ImmutableMap.<String, String>builder();
                for (Map<String, Object> p : props) {
                    builder.put((String) p.get("k"), (String) p.get("v"));
                }
                customModProperties = builder.build();
            } else {
                customModProperties = EMPTY_PROPERTIES;
            }

            Boolean hasDisableableFlag = (Boolean) descriptor.get("canBeDeactivated");
            boolean hasReverseDepends = !event.getReverseDependencies().get(getModId()).isEmpty();
            if (hasDisableableFlag != null && hasDisableableFlag) {
                disableability = hasReverseDepends ? Disableable.DEPENDENCIES : Disableable.YES;
            } else {
                disableability = hasReverseDepends ? Disableable.DEPENDENCIES : Disableable.RESTART;
            }
            Method factoryMethod = gatherAnnotations(clazz);
            modInstance = getLanguageAdapter().getNewInstance(this, clazz, modClassLoader, factoryMethod);
            NetworkRegistry.INSTANCE.register(this, clazz, (String) (descriptor.containsKey("acceptableRemoteVersions") ? descriptor.get("acceptableRemoteVersions") : null), event.getASMHarvestedData());
            if (fingerprintNotPresent) {
                eventBus.post(new FMLFingerprintViolationEvent(source.isDirectory(), source, ImmutableSet.copyOf(this.sourceFingerprints), expectedFingerprint));
            }
            ProxyInjector.inject(this, event.getASMHarvestedData(), FMLCommonHandler.instance().getSide(), getLanguageAdapter());
            processFieldAnnotations(event.getASMHarvestedData());
        } catch (Throwable e) {
            controller.errorOccurred(this, e);
        }
    }

    @Subscribe
    public void handleModStateEvent(FMLEvent event) {
        if (!eventMethods.containsKey(event.getClass())) {
            return;
        }
        try {
            for (Method m : eventMethods.get(event.getClass())) {
                m.invoke(modInstance, event);
            }
        } catch (Throwable t) {
            controller.errorOccurred(this, t);
        }
    }

    @Override
    public ArtifactVersion getProcessedVersion() {
        if (processedVersion == null) {
            processedVersion = new DefaultArtifactVersion(getModId(), getVersion());
        }
        return processedVersion;
    }

    @Override
    public boolean isImmutable() {
        return false;
    }

    @Override
    public String getDisplayVersion() {
        return modMetadata.version;
    }

    @Override
    public VersionRange acceptableMinecraftVersionRange() {
        return minecraftAccepted;
    }

    @Override
    public Certificate getSigningCertificate() {
        return certificate;
    }

    @Override
    public String toString() {
        return "FMLMod:" + getModId() + "{" + getVersion() + "}";
    }

    @Override
    public Map<String, String> getCustomModProperties() {
        return customModProperties;
    }

    @Override
    public Class<?> getCustomResourcePackClass() {
        try {
            return getSource().isDirectory() ? Class.forName("net.minecraftforge.fml.client.FMLFolderResourcePack", true, getClass().getClassLoader()) : Class.forName("net.minecraftforge.fml.client.FMLFileResourcePack", true, getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    @Override
    public Map<String, String> getSharedModDescriptor() {
        Map<String, String> descriptor = Maps.newHashMap();
        descriptor.put("modsystem", "Blacksmith");
        descriptor.put("id", getModId());
        descriptor.put("version", getDisplayVersion());
        descriptor.put("name", getName());
        descriptor.put("url", modMetadata.url);
        descriptor.put("authors", modMetadata.getAuthorList());
        descriptor.put("description", modMetadata.description);
        return descriptor;
    }

    @Override
    public Disableable canBeDisabled() {
        return disableability;
    }

    @Override
    public String getGuiClassName() {
        return (String) descriptor.get("guiFactory");
    }

    @Override
    public List<String> getOwnedPackages() {
        return candidate.getContainedPackages();
    }

    private boolean isTrue(Boolean value) {
        if (value == null) {
            return false;
        }
        return value.booleanValue();
    }

    @Override
    public boolean shouldLoadInEnvironment() {
        boolean clientSideOnly = isTrue((Boolean) descriptor.get("clientSideOnly"));
        boolean serverSideOnly = isTrue((Boolean) descriptor.get("serverSideOnly"));

        if (clientSideOnly && serverSideOnly) {
            throw new RuntimeException("Mod annotation claims to be both client and server side only!");
        }

        Side side = FMLCommonHandler.instance().getSide();

        if (clientSideOnly && side != Side.CLIENT) {
            FMLLog.info("Disabling mod %s it is client side only.", getModId());
            return false;
        }

        if (serverSideOnly && side != Side.SERVER) {
            FMLLog.info("Disabling mod %s it is server side only.", getModId());
            return false;
        }

        return true;
    }

    @Override
    public URL getUpdateUrl() {
        return updateJSONUrl;
    }
}
