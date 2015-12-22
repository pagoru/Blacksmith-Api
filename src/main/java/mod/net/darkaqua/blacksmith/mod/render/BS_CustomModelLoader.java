package net.darkaqua.blacksmith.mod.render;

import net.darkaqua.blacksmith.mod.registry.RenderRegistry;
import net.darkaqua.blacksmith.mod.util.Log;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cout970 on 07/12/2015.
 */
public class BS_CustomModelLoader implements ICustomModelLoader{

    public static final BS_CustomModelLoader INSTANCE = new BS_CustomModelLoader();
    private Map<ResourceLocation, IModel> cache;

    private BS_CustomModelLoader(){
        cache = new HashMap<>();
    }

    @Override
    public boolean accepts(ResourceLocation modelLocation) {
        boolean result = RenderRegistry.INSTANCE.hasModelForLocation(modelLocation);
        if(!modelLocation.getResourceDomain().contains("minecraft")) {
            Log.debug("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            Log.debug("Acepts? " + modelLocation);
            Log.debug(result);
            Log.debug("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        }
        return result;
    }

    @Override
    public IModel loadModel(ResourceLocation modelLocation) throws IOException {
        if(!cache.containsKey(modelLocation)){

            Log.debug("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            Log.debug("Loading Model: "+modelLocation);
            Log.debug("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

            IModel model = RenderRegistry.INSTANCE.getModel(modelLocation);
            cache.put(modelLocation, model);
        }
        return cache.get(modelLocation);
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {
        cache.clear();
    }
}
