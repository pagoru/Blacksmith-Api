package net.darkaqua.blacksmith.mod.render;

import net.darkaqua.blacksmith.api.render.model.generated.IGenModel;
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
    private IResourceManager manager;
    private Map<ResourceLocation, IModel> cache;

    private BS_CustomModelLoader(){
        cache = new HashMap<>();
    }

    @Override
    public boolean accepts(ResourceLocation modelLocation) {
        if(modelLocation.getResourceDomain().contains("blacksmith")) {
            Log.debug("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            Log.debug("Acepts? " + modelLocation);
            Log.debug(RenderRegistry.INSTANCE.getRegisteredModels());
            Log.debug("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        }
        return RenderRegistry.INSTANCE.getRegisteredModels().containsKey(modelLocation);
    }

    @Override
    public IModel loadModel(ResourceLocation modelLocation) throws IOException {
        if(!cache.containsKey(modelLocation)){
            IGenModel model = RenderRegistry.INSTANCE.getRegisteredModels().get(modelLocation);
            if (model != null){
                cache.put(modelLocation, new BS_GeneratedModel(model));
            }
            Log.debug("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            Log.debug("Registering Model: "+model);
            Log.debug(RenderRegistry.INSTANCE.getRegisteredModels());
            Log.debug("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        }
        return cache.get(modelLocation);
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {
        manager = resourceManager;
        cache.clear();
    }
}
