package net.darkaqua.blacksmith.mod.modloader;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import net.darkaqua.blacksmith.api.modloader.ModInstance;
import net.darkaqua.blacksmith.api.modloader.ModSidedProxy;
import net.darkaqua.blacksmith.mod.util.Log;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.discovery.ModCandidate;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.versioning.ArtifactVersion;
import net.minecraftforge.fml.common.versioning.DefaultArtifactVersion;
import net.minecraftforge.fml.common.versioning.VersionRange;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BlacksmithModContainer implements ModContainer {

    public final String modClass;
    public final ModCandidate modCandidate;
    public final Map<String, Object> modDescriptor;
    public final File source;
    private final String modLanguage;
    private final ILanguageAdapter languageAdapter;

    public ModMetadata metadata;
    public boolean enabled = true;

    public EventBus eventBus;
    public LoadController controller;
    public Object modInstance;


    public BlacksmithModContainer(String className, ModCandidate candidate, Map<String, Object> descriptor) {
        this.modClass = className;
        this.modCandidate = candidate;
        this.modDescriptor = descriptor;
        source = candidate.getModContainer();
        //TODO join this class with FMLModContainer

        //default FML code, will work for now
        this.modLanguage = (String) modDescriptor.get("modLanguage");
        String languageAdapterType = (String)modDescriptor.get("modLanguageAdapter");
        if (Strings.isNullOrEmpty(languageAdapterType)){
            this.languageAdapter = "scala".equals(modLanguage) ? new ILanguageAdapter.ScalaAdapter() : new ILanguageAdapter.JavaAdapter();
        }else{
            try{
                this.languageAdapter = (ILanguageAdapter)Class.forName(languageAdapterType, true, Loader.instance().getModClassLoader()).newInstance();
                FMLLog.finer("Using custom language adapter %s (type %s) for %s (modid %s)", this.languageAdapter, languageAdapterType, this.modClass, getModId());
            }catch (Exception ex){
                FMLLog.log(Level.ERROR, ex, "Error constructing custom mod language adapter %s (referenced by %s) (modid: %s)", languageAdapterType, this.modClass, getModId());
                throw new LoaderException(ex);
            }
        }
    }

    @Subscribe
    public void constructMod(FMLConstructionEvent event) {
        try {
            ModClassLoader modClassLoader = event.getModClassLoader();
            modClassLoader.addFile(source);
            modClassLoader.clearNegativeCacheFor(modCandidate.getClassList());
            Class<?> clazz = Class.forName(modClass, true, modClassLoader);
            modInstance = clazz.newInstance();
            ModLoaderManager.registerPlugin(this, modInstance);
            try {
                for (Field f : clazz.getDeclaredFields()) {
                    if (f.isAnnotationPresent(ModInstance.class)) {
                        f.set(modInstance, modInstance);
                    }
                }
            }catch (ReflectiveOperationException e){
                Log.warn("Error trying to place a mod instance in a field marked with @ModInstance: "+clazz);
            }
;
            CustomProxyInjector.inject(this, event.getASMHarvestedData(), FMLCommonHandler.instance().getSide(), languageAdapter);
        } catch (InstantiationException | MalformedURLException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getModId() {
        return (String) modDescriptor.get("id");
    }

    @Override
    public String getName() {
        return (String) modDescriptor.get("name");
    }

    @Override
    public String getVersion() {
        return (String) modDescriptor.get("version");
    }

    @Override
    public File getSource() {
        return modCandidate.getModContainer();
    }

    @Override
    public ModMetadata getMetadata() {
        return metadata;
    }

    @Override
    public void bindMetadata(MetadataCollection mc) {

        metadata = mc.getMetadataForId(getModId(), this.modDescriptor);

        String annotationDependencies = (String) this.modDescriptor.get("dependencies");

        Set<ArtifactVersion> requirements = Sets.newHashSet();
        List<ArtifactVersion> dependencies = Lists.newArrayList();
        List<ArtifactVersion> dependants = Lists.newArrayList();

        Loader.instance().computeDependencies(annotationDependencies, requirements, dependencies, dependants);

        metadata.requiredMods = requirements;
        metadata.dependencies = dependencies;
        metadata.dependants = dependants;
    }

    @Override
    public void setEnabledState(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Set<ArtifactVersion> getRequirements() {
        return this.metadata.requiredMods;
    }

    @Override
    public List<ArtifactVersion> getDependencies() {
        return this.metadata.dependencies;
    }

    @Override
    public List<ArtifactVersion> getDependants() {
        return this.metadata.dependants;
    }

    @Override
    public String getSortingRules() {
        return (String) this.modDescriptor.get("dependencies");
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        if (enabled) {
            eventBus = bus;
            this.controller = controller;
            bus.register(this);
            return true;
        }
        return false;
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
    public ArtifactVersion getProcessedVersion() {
        return new DefaultArtifactVersion(getModId(), getVersion());
    }

    @Override
    public boolean isImmutable() {
        return true;
    }

    @Override
    public String getDisplayVersion() {
        return getVersion();
    }

    @Override
    public VersionRange acceptableMinecraftVersionRange() {
        return Loader.instance().getMinecraftModContainer().getStaticVersionRange();
    }

    @Override
    public Certificate getSigningCertificate() {
        return null;
    }

    @Override
    public Map<String, String> getCustomModProperties() {
        return EMPTY_PROPERTIES;
    }

    @Override
    public Class<?> getCustomResourcePackClass() {
        try
        {
            return getSource().isDirectory() ? Class.forName("net.minecraftforge.fml.client.FMLFolderResourcePack", true, getClass().getClassLoader()) : Class.forName("net.minecraftforge.fml.client.FMLFileResourcePack",true, getClass().getClassLoader());
        }
        catch (ClassNotFoundException e)
        {
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
        return descriptor;
    }

    @Override
    public Disableable canBeDisabled() {
        return Disableable.RESTART;
    }

    @Override
    public String getGuiClassName() {
        return "";
    }

    @Override
    public List<String> getOwnedPackages() {
        return modCandidate.getContainedPackages();
    }

    @Override
    public boolean shouldLoadInEnvironment() {
        return true;
    }

    @Override
    public URL getUpdateUrl() {
        String s = (String) modDescriptor.get("updateURL");
        if (s == null || s.equals("")) {
            return null;
        }
        try {
            return new URL(s);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Blacksmith Mod: " + getName() + " (" + getVersion() + ")";
    }


    public static class CustomProxyInjector {

        public static void inject(ModContainer mod, ASMDataTable data, Side side, ILanguageAdapter languageAdapter)
        {
            FMLLog.fine("Attempting to inject @ModSidedProxy classes into %s", mod.getModId());
            Set<ASMDataTable.ASMData> targets = data.getAnnotationsFor(mod).get(ModSidedProxy.class.getName());
            ModClassLoader mcl = Loader.instance().getModClassLoader();

            for (ASMDataTable.ASMData targ : targets)
            {
                try
                {
                    Class<?> proxyTarget = Class.forName(targ.getClassName(), true, mcl);
                    Field target = proxyTarget.getDeclaredField(targ.getObjectName());
                    if (target == null)
                    {
                        // Impossible?
                        FMLLog.severe("Attempted to load a proxy type into %s.%s but the field was not found", targ.getClassName(), targ.getObjectName());
                        throw new LoaderException(String.format("Attempted to load a proxy type into %s.%s but the field was not found", targ.getClassName(), targ.getObjectName()));
                    }
                    target.setAccessible(true);

                    ModSidedProxy annotation = target.getAnnotation(ModSidedProxy.class);
                    if (!Strings.isNullOrEmpty(annotation.modId()) && !annotation.modId().equals(mod.getModId()))
                    {
                        FMLLog.fine("Skipping proxy injection for %s.%s since it is not for mod %s", targ.getClassName(), targ.getObjectName(), mod.getModId());
                        continue;
                    }
                    String targetType = side.isClient() ? annotation.clientSide() : annotation.serverSide();
                    Object proxy=Class.forName(targetType, true, mcl).newInstance();

                    if (languageAdapter.supportsStatics() && (target.getModifiers() & Modifier.STATIC) == 0 )
                    {
                        FMLLog.severe("Attempted to load a proxy type %s into %s.%s, but the field is not static", targetType, targ.getClassName(), targ.getObjectName());
                        throw new LoaderException(String.format("Attempted to load a proxy type %s into %s.%s, but the field is not static", targetType, targ.getClassName(), targ.getObjectName()));
                    }
                    if (!target.getType().isAssignableFrom(proxy.getClass()))
                    {
                        FMLLog.severe("Attempted to load a proxy type %s into %s.%s, but the types don't match", targetType, targ.getClassName(), targ.getObjectName());
                        throw new LoaderException(String.format("Attempted to load a proxy type %s into %s.%s, but the types don't match", targetType, targ.getClassName(), targ.getObjectName()));
                    }
                    languageAdapter.setProxy(target, proxyTarget, proxy);
                }
                catch (Exception e)
                {
                    FMLLog.log(Level.ERROR, e, "An error occured trying to load a proxy into %s.%s", targ.getAnnotationInfo(), targ.getClassName(), targ.getObjectName());
                    throw new LoaderException(e);
                }
            }

            // Allow language specific proxy injection.
            languageAdapter.setInternalProxies(mod, side, mcl);
        }
    }
}
