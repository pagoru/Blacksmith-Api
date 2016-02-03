package net.darkaqua.blacksmith.mod.registry;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.api.modloader.IModIdentifier;
import net.darkaqua.blacksmith.api.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.render.model.IModelPart;
import net.darkaqua.blacksmith.api.render.model.IPartIdentifier;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.mod.render.ModelPartIdentifier;
import net.darkaqua.blacksmith.mod.render.model.IBakedModelPart;
import net.darkaqua.blacksmith.mod.render.model.IModelBuilder;
import net.darkaqua.blacksmith.mod.render.model.ItemLayerModelBuilder;
import net.darkaqua.blacksmith.mod.render.model.ModelPartBuilder;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by cout970 on 22/12/2015.
 */
public class ModelRegistry implements IModelRegistry {

    public static final ModelRegistry INSTANCE = new ModelRegistry();
    private static Map<IPartIdentifier, IModelBuilder> modelBuilders = new HashMap<>();
    private static Map<IPartIdentifier, IBakedModelPart> bakedModels = new HashMap<>();
    private static Map<String, Integer> modelNumbers = new HashMap<>();

    private ModelRegistry() {

    }

    @SubscribeEvent
    public void onTextureLoad(TextureStitchEvent.Pre event) {
        for (IModelBuilder b : modelBuilders.values()) {
            b.onTexturesLoad(event.map);
        }
    }

    public static void bakeModels() {
        bakedModels.clear();
        for (Map.Entry<IPartIdentifier, IModelBuilder> e : modelBuilders.entrySet()) {
            bakedModels.put(e.getKey(), e.getValue().build());
        }
    }

    @Override
    public IPartIdentifier registerModelPart(IModIdentifier mod, IModelPart model) {
        if (model == null)
            throw new NullPointerException("Attempt to register a null IModelPart");
        if (mod == null)
            throw new NullPointerException("Invalid IModIdentifier in IModelRegistry.registerModelPart: null");

        IPartIdentifier id = generateIdentifier(mod.getModID());
        modelBuilders.put(id, new ModelPartBuilder(model));
        return id;
    }

    @Override
    public IPartIdentifier registerFlatItemModel(IModIdentifier mod, ResourceReference... texture) {
        if (texture.length == 0)
            throw new IllegalArgumentException("Attempt to register 0 textures in IModelRegistry.registerFlatItemModel");
        if (mod == null)
            throw new NullPointerException("Invalid IModIdentifier in IModelRegistry.registerFlatItemModel: null");

        IPartIdentifier id = generateIdentifier(mod.getModID());
        modelBuilders.put(id, new ItemLayerModelBuilder(Lists.newArrayList(texture).stream().map(MCInterface::toResourceLocation).collect(Collectors.toList())));
        return id;
    }

    private IPartIdentifier generateIdentifier(String modId) {
        int number = 0;
        if (modelNumbers.containsKey(modId)) {
            number = modelNumbers.get(modId) + 1;
            modelNumbers.put(modId, number);
        } else {
            modelNumbers.put(modId, 0);
        }
        return new ModelPartIdentifier(modId, number);
    }

    public IBakedModelPart getBakedModelPart(IPartIdentifier id) {
        return bakedModels.get(id);
    }

    public void reloadResources() {
        modelBuilders.clear();
    }
}
