package net.darkaqua.blacksmith.mod.modloader;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.discovery.ModCandidate;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.versioning.ArtifactVersion;
import net.minecraftforge.fml.common.versioning.DefaultArtifactVersion;
import net.minecraftforge.fml.common.versioning.VersionRange;

import java.io.File;
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

}
