package net.darkaqua.blacksmith.mod.util;

import net.darkaqua.blacksmith.mod.Blacksmith;
import net.darkaqua.blacksmith.mod.registry.RenderRegistry;
import net.darkaqua.blacksmith.mod.render.JsonCreator;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Set;

/**
 * Created by cout970 on 26/11/2015.
 */
public class BS_ResourceLoader implements IResourcePack, IResourceManagerReloadListener{

    public static final BS_ResourceLoader INSTANCE = new BS_ResourceLoader();
    private Set<String> cache;

    private BS_ResourceLoader() {}

    @Override
    public InputStream getInputStream(ResourceLocation res) throws IOException {

        File file = getFile(res);
        return new BufferedInputStream(new FileInputStream(file));
    }

    private File getFile(ResourceLocation res){
        return JsonCreator.getFile(res.getResourceDomain(), res.getResourcePath());
    }

    @Override
    public boolean resourceExists(ResourceLocation res) {
        if(getResourceDomains().contains(res.getResourceDomain()) || res.getResourceDomain().contains(Blacksmith.MOD_ID)){
            File f = getFile(res);
            return f.exists();
        }
        return false;
    }

    @Override
    public Set getResourceDomains() {
        if(cache == null) {
            cache = RenderRegistry.INSTANCE.getRegisteredDomains();
        }
        return cache;
    }

    @Override
    public IMetadataSection getPackMetadata(IMetadataSerializer p_135058_1_, String p_135058_2_) throws IOException {
        return null;
    }

    @Override
    public BufferedImage getPackImage() throws IOException {
        return null;
    }

    @Override
    public String getPackName() {
        return Blacksmith.MOD_ID+" Resource Loader";
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {
        SimpleReloadableResourceManager res = (SimpleReloadableResourceManager) resourceManager;
        res.reloadResourcePack(BS_ResourceLoader.INSTANCE);
        cache = null;
    }
}
