package net.darkaqua.blacksmith.mod.render;

import net.darkaqua.blacksmith.mod.Blacksmith;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.SimpleResource;
import net.minecraft.util.ResourceLocation;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by cout970 on 26/11/2015.
 */
public class BS_ResourceLoader implements IResourceManager {

    public static final BS_ResourceLoader INSTANE = new BS_ResourceLoader();
    private RenderManager manager;

    private BS_ResourceLoader() {
        manager = RenderManager.INSTANCE;
    }

    public InputStream getInputStream(ResourceLocation res) throws IOException {
        String path = res.getResourcePath();
        File file = ModelUtils.getFile(res.getResourceDomain(),
                path.substring(path.lastIndexOf("/"),path.length()), path.substring(path.lastIndexOf("/")));
        return new BufferedInputStream(new FileInputStream(file));
    }

    @Override
    public Set getResourceDomains() {
        return manager.getRegisteredDomains();
    }

    @Override
    public List getAllResources(ResourceLocation res) throws IOException {
        LinkedList list = new LinkedList();
        for (ResourceLocation loc : manager.registeredResourceLocations()){
            if(loc.getResourceDomain().equals(res.getResourceDomain())){
                if(loc.getResourcePath().contains(res.getResourcePath())){
                    list.add(getResource(loc));
                }
            }
        }
        return list;
    }

    public IResource getResource(ResourceLocation res) throws IOException {
        return new SimpleResource(Blacksmith.MOD_NAME + " Resource", res,
                getInputStream(res), null, null);
    }
}
