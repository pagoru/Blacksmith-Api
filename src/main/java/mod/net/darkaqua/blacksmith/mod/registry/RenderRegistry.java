package net.darkaqua.blacksmith.mod.registry;

import net.darkaqua.blacksmith.api.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.item.IItemDefinition;
import net.darkaqua.blacksmith.api.registry.IRenderRegistry;
import net.darkaqua.blacksmith.api.render.model.IBlockModelProvider;
import net.darkaqua.blacksmith.api.render.model.IItemModelProvider;
import net.darkaqua.blacksmith.api.render.model.IModelIdentifier;
import net.darkaqua.blacksmith.api.render.model.IRenderModel;
import net.darkaqua.blacksmith.api.render.tileentity.ITileEntityRenderer;
import net.darkaqua.blacksmith.api.tileentity.ITileEntityDefinition;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.mod.Blacksmith;
import net.darkaqua.blacksmith.mod.exceptions.BlacksmithInternalException;
import net.darkaqua.blacksmith.mod.modloader.BlacksmithModContainer;
import net.darkaqua.blacksmith.mod.modloader.ModLoaderManager;
import net.darkaqua.blacksmith.mod.render.JsonCreator;
import net.darkaqua.blacksmith.mod.render.util.ModelIdentifier;
import net.darkaqua.blacksmith.mod.util.Log;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
    private static Map<Class<? extends ITileEntityDefinition>, ITileEntityRenderer> tileRenders = new HashMap<>();

    private RenderRegistry() {
    }

    @Override
    public boolean registerBlockModelProvider(IBlockDefinition def, final IBlockModelProvider provider) {

        if (ModLoaderManager.getLoadingState() != ModLoaderManager.LoadingState.PREINIT) {
            throw new IllegalStateException("Block models should be registered only in preInit");
        }
        BlacksmithModContainer mod = ModLoaderManager.getActiveMod();
        if (mod == null)
            throw new BlacksmithInternalException("Invalid mod container in block model provider registration: null");

        BlockRegistry.RegisteredBlock reg = BlockRegistry.INSTANCE.getRegistrationData(def);
        if (reg == null)
            return false;
        final String domain = Blacksmith.MOD_ID;

        final ResourceLocation block = Block.blockRegistry.getNameForObject(reg.getBlock());
        Map<IModelIdentifier, IRenderModel> localModels = new HashMap<>();

        for (IRenderModel model : provider.getAllModels()) {
            ResourceLocation id = new ResourceLocation(domain, block.getResourcePath() + "/" + model.getName());
            ModelIdentifier identifier = new ModelIdentifier(id, model.getName());
            provider.bindModelIdentifier(model, identifier);
            localModels.put(identifier, model);
        }

        //itemblock
//        ModelBakery.addVariantName(reg.getItemBlock(), new ResourceLocation(domain, reg.getIdentifier().toLowerCase()).toString());
//        ModelLoader.setCustomModelResourceLocation(reg.getItemBlock(), 0, new ModelResourceLocation(domain+ ":" + reg.getIdentifier().toLowerCase(), "inventory"));
//        models.put(new ResourceLocation(domain, "models/item/"+reg.getIdentifier().toLowerCase()), model);
//        itemsToRegister.add(new ItemRenderRegister(reg.getItemBlock(), 0, new ModelResourceLocation(domain+ ":" + reg.getIdentifier().toLowerCase(), "inventory")));

        final Map<String, IModelIdentifier> ids = new HashMap<>();
        //block
        StateMapperBase stateMapper = new StateMapperBase() {

            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                IModelIdentifier identifier = provider.getModelForVariant(MCInterface.fromIBlockState(state));
                String variant = getPropertyString(state.getProperties());
                ids.put(variant, identifier);
                return new ModelResourceLocation(domain + ":" + block.getResourcePath(), variant);
            }
        };
        for (Object loc : stateMapper.putStateModelLocations(reg.getBlock()).values()) {
            ModelResourceLocation mloc = (ModelResourceLocation) loc;
            reg.addModel(mloc);
            IRenderModel model = localModels.get(ids.get(mloc.getVariant()));
            models.put(new ResourceLocation(mloc.getResourceDomain(), "models/block/" + mloc.getResourcePath()), model);
        }
        ModelLoader.setCustomStateMapper(reg.getBlock(), stateMapper);

        return true;
    }

    @Override
    public boolean registerItemModelProvider(IItemDefinition def, final IItemModelProvider provider) {

        if (ModLoaderManager.getLoadingState() != ModLoaderManager.LoadingState.PREINIT) {
            throw new IllegalStateException("Item models should be registered only in preInit");
        }
        BlacksmithModContainer mod = ModLoaderManager.getActiveMod();
        if (mod == null)
            throw new BlacksmithInternalException("Invalid mod container in item model provider registration: null");

        ItemRegistry.RegisteredItem reg = ItemRegistry.INSTANCE.getRegistrationData(def);
        if (reg == null)
            return false;
        final String domain = Blacksmith.MOD_ID;
        final ResourceLocation itemRes = Item.itemRegistry.getNameForObject(reg.getItem());
        Map<IModelIdentifier, IRenderModel> localModels = new HashMap<>();

        for (IRenderModel model : provider.getAllModels()) {
            ResourceLocation id = new ResourceLocation(domain, itemRes.getResourcePath() + "/" + model.getName());
            ModelIdentifier identifier = new ModelIdentifier(id, model.getName());
            provider.bindModelIdentifier(model, identifier);
            localModels.put(identifier, model);
        }

        ModelLoader.setCustomMeshDefinition(reg.getItem(), new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                IModelIdentifier identifier = provider.getModelForVariant(MCInterface.fromItemStack(stack));
                return new ModelResourceLocation(domain + ":" + itemRes.getResourcePath()+"/"+identifier.getVariant(), "inventory");
            }
        });
        for (Map.Entry<IModelIdentifier, IRenderModel> e : localModels.entrySet()) {
            ModelResourceLocation mloc = new ModelResourceLocation(e.getKey().getReference().toString(), e.getKey().getVariant());
            reg.addModel(mloc);
            IRenderModel model = e.getValue();
            Log.debug(e.getKey().getReference().toString());
            ModelLoader.addVariantName(reg.getItem(), e.getKey().getReference().toString());
            models.put(new ResourceLocation(mloc.getResourceDomain(), "models/item/" + mloc.getResourcePath()), model);
        }

        return true;
    }

    @Override
    public boolean registerTileEntityRenderer(Class<? extends ITileEntityDefinition> def, ITileEntityRenderer renderer) {
        if (tileRenders.containsKey(def))
            return false;
        if (renderer == null) {
            return false;
        }
        tileRenders.put(def, renderer);
        return true;
    }

    @Override
    public IModelIdentifier registerModelFile(ResourceReference file) {
        return null;
    }

    @Override
    public IModelIdentifier registerFlatItemModel(ResourceReference texture) {
        return null;
    }

    public ITileEntityRenderer getTileEntityRenderer(Class<? extends ITileEntityDefinition> def) {
        return tileRenders.get(def);
    }

    public void onInit() {
        for (ItemRenderRegister r : itemsToRegister) {
            Minecraft.getMinecraft()
                    .getRenderItem()
                    .getItemModelMesher()
                    .register(r.getItem(), r.getMeta(), r.getLocation());
        }
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

    public void onPreInitFinish() {
        for (BlockRegistry.RegisteredBlock block : BlockRegistry.INSTANCE.getAllRegisteredBlocks()) {
            JsonCreator.createBlockStateJson(block);
        }
    }

    public static class ItemRenderRegister {

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
