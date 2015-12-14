package net.darkaqua.blacksmith.mod.registry;

import net.darkaqua.blacksmith.api.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.registry.IRenderRegistry;
import net.darkaqua.blacksmith.api.render.model.IBlockModelProvider;
import net.darkaqua.blacksmith.api.render.model.IModelIdentifier;
import net.darkaqua.blacksmith.api.render.model.IRenderModel;
import net.darkaqua.blacksmith.mod.Blacksmith;
import net.darkaqua.blacksmith.mod.modloader.BlacksmithModContainer;
import net.darkaqua.blacksmith.mod.modloader.ModLoaderManager;
import net.darkaqua.blacksmith.mod.render.JsonCreator;
import net.darkaqua.blacksmith.mod.render.util.ModelIdentifier;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import java.util.*;

/**
 * Created by cout970 on 07/12/2015.
 */
public class RenderRegistry implements IRenderRegistry {

    public static final RenderRegistry INSTANCE = new RenderRegistry();
    private static Map<ResourceLocation, IRenderModel> models = new HashMap<>();
    private static List<ItemRenderRegister> itemsToRegister = new LinkedList<>();

    private RenderRegistry() {
    }

    public Map<ResourceLocation, IRenderModel> getRegisteredModels() {
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
    public boolean registerBlockModelProvider(IBlockDefinition def, final IBlockModelProvider provider) {
        BlacksmithModContainer mod = ModLoaderManager.getActiveMod();
        if (mod == null || ModLoaderManager.getLoadingState() != ModLoaderManager.LoadingState.PREINIT) {
            throw new IllegalStateException("Block models should be registered only in preInit");
        }
        BlockRegistry.RegisteredBlock reg = BlockRegistry.INSTANCE.getRegistrationData(def);
        final String domain = Blacksmith.MOD_ID;

        final ResourceLocation block = (ResourceLocation) Block.blockRegistry.getNameForObject(reg.getMcBlock());
        Map<IModelIdentifier, IRenderModel> localModels = new HashMap<>();

        for(IRenderModel model : provider.getAllModels()){
            ResourceLocation id = new ResourceLocation(domain, block.getResourcePath()+"/"+model.getName());
            ModelIdentifier identifier = new ModelIdentifier(id);
            provider.bindModelIdentifier(model, identifier);
            localModels.put(identifier, model);
        }

        //itemblock
//        ModelBakery.addVariantName(reg.getItemBlock(), new ResourceLocation(domain, reg.getBlockIdentifier().toLowerCase()).toString());
//        ModelLoader.setCustomModelResourceLocation(reg.getItemBlock(), 0, new ModelResourceLocation(domain+ ":" + reg.getBlockIdentifier().toLowerCase(), "inventory"));
//        models.put(new ResourceLocation(domain, "models/item/"+reg.getBlockIdentifier().toLowerCase()), model);
//        itemsToRegister.add(new ItemRenderRegister(reg.getItemBlock(), 0, new ModelResourceLocation(domain+ ":" + reg.getBlockIdentifier().toLowerCase(), "inventory")));

        final Map<String, IModelIdentifier> ids = new HashMap<>();
        //block
        StateMapperBase stateMapper = new StateMapperBase() {

            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                IModelIdentifier identifier = provider.getModelForVariant(MCInterface.fromIBlockVariant(state));
                String variant = getPropertyString(state.getProperties());
                ids.put(variant, identifier);
                return new ModelResourceLocation(domain+":"+block.getResourcePath(), variant);
            }
        };
        for(Object loc : stateMapper.putStateModelLocations(reg.getMcBlock()).values()) {
            ModelResourceLocation mloc = (ModelResourceLocation) loc;
            reg.addBlockModel(mloc);
            IRenderModel model = localModels.get(ids.get(mloc.getVariant()));
            models.put(new ResourceLocation(mloc.getResourceDomain(), "models/block/"+mloc.getResourcePath()), model);
        }
        ModelLoader.setCustomStateMapper(reg.getMcBlock(), stateMapper);

        return true;
    }

    public void onInit(){
        for(ItemRenderRegister r : itemsToRegister){
            Minecraft.getMinecraft()
                    .getRenderItem()
                    .getItemModelMesher()
                    .register(r.getItem(), r.getMeta(), r.getLocation());
        }
    }

    public void onPreInitFinish() {
        for(BlockRegistry.RegisteredBlock block : BlockRegistry.INSTANCE.getAllRegisteredBlocks()) {
            JsonCreator.createBlockStateJson(block);
        }
    }

    public static class ItemRenderRegister{

        private Item item;
        private int meta;
        private ModelResourceLocation location;

        public ItemRenderRegister(Item item, int meta, ModelResourceLocation location) {
            this.item = item;
            this.meta = meta;
            this.location = location;
        }

        public Item getItem() {
            return item;
        }

        public int getMeta() {
            return meta;
        }

        public ModelResourceLocation getLocation() {
            return location;
        }
    }
}
