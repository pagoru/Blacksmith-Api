package net.darkaqua.blacksmith.mod.registry;

import com.google.common.collect.ImmutableList;
import net.darkaqua.blacksmith.api.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.render.model.IModelIdentifier;
import net.darkaqua.blacksmith.api.render.model.IRenderModel;
import net.darkaqua.blacksmith.api.render.model.IRenderTransformationProvider;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.mod.Blacksmith;
import net.darkaqua.blacksmith.mod.exceptions.BlacksmithInternalException;
import net.darkaqua.blacksmith.mod.modloader.BlacksmithModContainer;
import net.darkaqua.blacksmith.mod.modloader.ModLoaderManager;
import net.darkaqua.blacksmith.mod.render.BS_GeneratedModel;
import net.darkaqua.blacksmith.mod.render.BS_ItemLayerModel;
import net.darkaqua.blacksmith.mod.render.ModelIdentifier;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cout970 on 22/12/2015.
 */
public class ModelRegistry implements IModelRegistry {

    public static final ModelRegistry INSTANCE = new ModelRegistry();
    private static final Map<IModelIdentifier, IModel> models = new HashMap<>();
    private static final Map<IModelIdentifier, IRenderModel> renderModels = new HashMap<>();
    private static final Map<IModelIdentifier, FlatItemModel> flatItemModels = new HashMap<>();
    private static final Map<String, Integer> modelNames = new HashMap<>();

    private ModelRegistry() {}

    @Override
    public IModelIdentifier registerRenderModel(IRenderModel model) {
        if(model == null){
            throw new NullPointerException("Invalid IRenderModel: null");
        }
        if (ModLoaderManager.getLoadingState() != ModLoaderManager.LoadingState.PREINIT) {
            throw new IllegalStateException("Item models should be registered only in preInit");
        }
        BlacksmithModContainer mod = ModLoaderManager.getActiveMod();
        if (mod == null)
            throw new BlacksmithInternalException("Invalid mod container in ModelRegistry.registerRenderModel: null");

        ModelIdentifier identifier = new ModelIdentifier(new ResourceLocation(Blacksmith.MOD_ID, mod.getModId()+"/"+model.getName()+"_"+getNumber(model.getName())), model.getName());
        renderModels.put(identifier, model);
        models.put(identifier, new BS_GeneratedModel(model));
        return identifier;
    }

    @Override
    public IModelIdentifier registerFlatItemModel(ResourceReference texture, IRenderTransformationProvider provider) {
        if(texture == null || provider == null){
            throw new NullPointerException("Invalid ResourceReference or IRenderTransformationProvider: null");
        }
        if (ModLoaderManager.getLoadingState() != ModLoaderManager.LoadingState.PREINIT) {
            throw new IllegalStateException("Item models should be registered only in preInit");
        }
        BlacksmithModContainer mod = ModLoaderManager.getActiveMod();
        if (mod == null)
            throw new BlacksmithInternalException("Invalid mod container in ModelRegistry.registerFlatItemModel: null");

        ModelIdentifier identifier = new ModelIdentifier(new ResourceLocation(Blacksmith.MOD_ID, mod.getModId()+"/"+texture.getPath()), "FlatItemModel");
        flatItemModels.put(identifier, new FlatItemModel(MCInterface.toResourceLocation(texture), provider));
        models.put(identifier, new BS_ItemLayerModel(ImmutableList.of(MCInterface.toResourceLocation(texture)), provider));
        return identifier;
    }

    public IModel getModel(IModelIdentifier identifier) {
        return models.get(identifier);
    }

    public IRenderModel getRenderModel(IModelIdentifier i){
        return renderModels.get(i);
    }

    private int getNumber(String name) {
        if(modelNames.containsKey(name)){
            int i = modelNames.get(name)+1;
            modelNames.put(name, i);
            return i;
        }else{
            modelNames.put(name, 0);
            return 0;
        }
    }

    public FlatItemModel getFlatItemModel(IModelIdentifier i){
        return flatItemModels.get(i);
    }

    public static class FlatItemModel {

        private ResourceLocation texture;
        private IRenderTransformationProvider modelTransformation;

        public FlatItemModel(ResourceLocation texture, IRenderTransformationProvider modelTransformation) {
            this.texture = texture;
            this.modelTransformation = modelTransformation;
        }

        public ResourceLocation getTexture() {
            return texture;
        }

        public IRenderTransformationProvider getModelTransformation() {
            return modelTransformation;
        }
    }
}
