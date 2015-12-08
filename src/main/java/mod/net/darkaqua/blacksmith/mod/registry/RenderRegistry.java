package net.darkaqua.blacksmith.mod.registry;

import net.darkaqua.blacksmith.api.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.registry.IRenderRegistry;
import net.darkaqua.blacksmith.api.render.model.generated.IGenModel;
import net.darkaqua.blacksmith.api.render.model.json.IJsonModelWrapper;
import net.darkaqua.blacksmith.mod.modloader.BlacksmithModContainer;
import net.darkaqua.blacksmith.mod.modloader.ModLoaderManager;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by cout970 on 07/12/2015.
 */
public class RenderRegistry implements IRenderRegistry {

    public static final RenderRegistry INSTANCE = new RenderRegistry();
    private static Map<ResourceLocation, IGenModel> models = new HashMap<>();

    private RenderRegistry() {
    }

    public Map<ResourceLocation, IGenModel> getRegisteredModels() {
        return models;
    }

    public Set<String> getRegisteredDomains() {
        HashSet<String> domains = new HashSet<>();
        for (ResourceLocation res : models.keySet()) {
            domains.add(res.getResourceDomain());
        }
        return domains;
    }

    @Override
    public boolean registerCustomBlockModel(IBlockDefinition def, IGenModel model) {
        BlacksmithModContainer curerent_mod = ModLoaderManager.getActiveMod();
        if (curerent_mod == null) {
            throw new IllegalStateException("Block models only can be registered in preInit");
        }
        BlockRegistry.RegisteredBlock reg = BlockRegistry.INSTANCE.getRegistrationData(def);

//        ModelLoader.setCustomStateMapper(reg.getMcBlock(), new IStateMapper() {
//            @Override
//            public Map putStateModelLocations(Block p_178130_1_) {
//
//                Map<IBlockState, ModelResourceLocation> map = Maps.newHashMap();
//                map.put(reg.getMcBlock().getDefaultState(), new ModelResourceLocation(Blacksmith.MOD_ID + ":" + reg.getIdentifier(), "normal"));
//                models.put(new ModelResourceLocation(Blacksmith.MOD_ID + ":" + reg.getIdentifier(), "normal"), model);
//                return map;
//            }
//        });
//        ModelBakery.addVariantName(reg.getItemBlock(), new ResourceLocation(Blacksmith.MOD_ID, reg.getIdentifier()).toString());
//        ModelLoader.setCustomModelResourceLocation(reg.getItemBlock(), 0, new ModelResourceLocation(Blacksmith.MOD_ID + ":models/item/" + reg.getIdentifier(), "inventory"));
//        models.put(new ResourceLocation(Blacksmith.MOD_ID, "models/item/" + reg.getIdentifier()), model);
        return true;
    }

    @Override
    public boolean registerJsonBlockModel(IBlockDefinition def, IJsonModelWrapper model) {

        return false;
    }
}
