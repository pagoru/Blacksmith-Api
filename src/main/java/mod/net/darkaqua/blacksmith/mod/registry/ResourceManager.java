package net.darkaqua.blacksmith.mod.registry;

import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

/**
 * Created by cout970 on 20/12/2015.
 */
@SideOnly(Side.CLIENT)
public class ResourceManager implements IResourceManagerReloadListener, net.darkaqua.blacksmith.api.registry.IResourceManager {

    public static final ResourceManager INSTANCE = new ResourceManager();
    private IResourceManager vanillaResourceManager;

    private ResourceManager() {
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {
        vanillaResourceManager = resourceManager;
    }


    @Override
    public IResourceFile getResource(ResourceReference ref) throws IOException {
        IResource res = vanillaResourceManager.getResource(MCInterface.toResourceLocation(ref));
        if (res == null) return null;
        return new ResourceFile(res);
    }

    @Override
    public Set<String> getResourceDomains() {
        return vanillaResourceManager.getResourceDomains();
    }

    @Override
    public Object getInternalResourceManger() {
        return vanillaResourceManager;
    }

    public static class ResourceFile implements IResourceFile {

        private IResource resource;

        public ResourceFile(IResource resource) {
            this.resource = resource;
        }

        @Override
        public InputStream getInputStream() {
            return resource.getInputStream();
        }

        @Override
        public ResourceReference getResourceReference() {
            ResourceLocation loc = resource.getResourceLocation();
            return new ResourceReference(loc.getResourceDomain(), loc.getResourcePath());
        }

        @Override
        public Object getInternalResource() {
            return resource;
        }


    }
}
