package net.darkaqua.blacksmith.mod.registry;

import net.darkaqua.blacksmith.api.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.render.model.IModelPart;
import net.darkaqua.blacksmith.api.render.model.IModelPartIdentifier;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.mod.exceptions.BlacksmithInternalException;
import net.darkaqua.blacksmith.mod.modloader.BlacksmithModContainer;
import net.darkaqua.blacksmith.mod.modloader.ModLoaderManager;
import net.darkaqua.blacksmith.mod.render.ModelPartIdentifier;
import net.darkaqua.blacksmith.mod.render.model.IModelBuilder;
import net.darkaqua.blacksmith.mod.render.model.ItemLayerModelBuilder;
import net.darkaqua.blacksmith.mod.render.model.ModelPartBuilder;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cout970 on 22/12/2015.
 */
public class ModelRegistry implements IModelRegistry {

    public static final ModelRegistry INSTANCE = new ModelRegistry();
    private static Map<IModelPartIdentifier, IModelBuilder> modelBuilders = new HashMap<>();
    private static Map<IModelPartIdentifier, IBakedModel> bakedModels = new HashMap<>();
    private static Map<String, Integer> modelNumbers = new HashMap<>();

    private ModelRegistry() {
    }

    @SubscribeEvent
    public void onTextureLoad(final TextureStitchEvent.Pre event) {
        for (IModelBuilder b : modelBuilders.values()) {
            b.onTexturesLoad(event.map);
        }
    }

    public static void bakeModels() {
        bakedModels.clear();
        for (Map.Entry<IModelPartIdentifier, IModelBuilder> e : modelBuilders.entrySet()) {
            bakedModels.put(e.getKey(), e.getValue().build());
        }
    }

    @Override
    public IModelPartIdentifier registerModelPart(IModelPart model) {
        if (model == null)
            throw new NullPointerException("Attempt to register a null IModelPart");
        if (ModLoaderManager.getLoadingState() != ModLoaderManager.LoadingState.PREINIT) {
            throw new IllegalStateException("Models should be registered only in preInit");
        }
        BlacksmithModContainer mod = ModLoaderManager.getActiveMod();
        if (mod == null)
            throw new BlacksmithInternalException("Invalid mod container in IModelRegistry.registerModelPart: null");

        IModelPartIdentifier id = generateIdentifier(mod.getModId());
        modelBuilders.put(id, new ModelPartBuilder(model));
        return id;
    }

    @Override
    public IModelPartIdentifier registerFlatItemModel(ResourceReference texture) {
        if (texture == null)
            throw new NullPointerException("Attempt to register a null texture");
        if (ModLoaderManager.getLoadingState() != ModLoaderManager.LoadingState.PREINIT) {
            throw new IllegalStateException("Models should be registered only in preInit");
        }
        BlacksmithModContainer mod = ModLoaderManager.getActiveMod();
        if (mod == null)
            throw new BlacksmithInternalException("Invalid mod container in IModelRegistry.registerFlatItemModel: null");

        IModelPartIdentifier id = generateIdentifier(mod.getModId());
        modelBuilders.put(id, new ItemLayerModelBuilder(MCInterface.toResourceLocation(texture)));
        return id;
    }

    public IBakedModel getBakedModel(IModelPartIdentifier id) {
        return bakedModels.get(id);
    }

    private IModelPartIdentifier generateIdentifier(String modId) {
        int number = 0;
        if (modelNumbers.containsKey(modId)) {
            number = modelNumbers.get(modId) + 1;
            modelNumbers.put(modId, number);
        } else {
            modelNumbers.put(modId, 0);
        }
        return new ModelPartIdentifier(modId, number);
    }
}
