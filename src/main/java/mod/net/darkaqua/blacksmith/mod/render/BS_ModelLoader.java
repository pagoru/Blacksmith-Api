package net.darkaqua.blacksmith.mod.render;

import com.google.common.eventbus.Subscribe;
import net.darkaqua.blacksmith.api.util.Log;
import net.darkaqua.blacksmith.mod.Blacksmith;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;

import java.io.IOException;

/**
 * Created by cout970 on 24/11/2015.
 */
public class BS_ModelLoader implements ICustomModelLoader {

    public static final BS_ModelLoader INSTANCE = new BS_ModelLoader();
    private ModelLoader loader;

    private BS_ModelLoader(){}

    @Subscribe
    public void loadModelLoader(ModelBakeEvent e){
        loader = e.modelLoader;
    }

    @Override
    public boolean accepts(ResourceLocation modelLocation) {
        if(modelLocation.getResourceDomain().contains(Blacksmith.MOD_ID +"@")){
            Log.debug("MODEL LOADER =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            Log.debug(modelLocation);
            Log.debug("MODEL LOADER =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        }
        return false;
    }

    @Override
    public IModel loadModel(ResourceLocation modelLocation) throws IOException {
        return null;
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {

    }
}
