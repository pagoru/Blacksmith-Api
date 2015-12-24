package net.darkaqua.blacksmith.mod.registry;

import net.darkaqua.blacksmith.api.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.item.IItemDefinition;
import net.darkaqua.blacksmith.api.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.registry.IRenderRegistry;
import net.darkaqua.blacksmith.api.render.model.IBlockModelProvider;
import net.darkaqua.blacksmith.api.render.model.IItemModelProvider;
import net.darkaqua.blacksmith.api.render.model.IModelIdentifier;
import net.darkaqua.blacksmith.api.render.tileentity.ITileEntityRenderer;
import net.darkaqua.blacksmith.api.tileentity.ITileEntityDefinition;
import net.darkaqua.blacksmith.mod.Blacksmith;
import net.darkaqua.blacksmith.mod.exceptions.BlacksmithInternalException;
import net.darkaqua.blacksmith.mod.modloader.BlacksmithModContainer;
import net.darkaqua.blacksmith.mod.modloader.ModLoaderManager;
import net.darkaqua.blacksmith.mod.render.JsonCreator;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;

import java.util.*;

/**
 * Created by cout970 on 07/12/2015.
 */
public class RenderRegistry implements IRenderRegistry {

    public static final RenderRegistry INSTANCE = new RenderRegistry();
    private static ModelRegistry modelRegistry = ModelRegistry.INSTANCE;
    private static Map<ResourceLocation, IModelIdentifier> locationToIdentifier = new HashMap<>();
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

        final BlockRegistry.RegisteredBlock reg = BlockRegistry.INSTANCE.getRegistrationData(def);

        if (reg == null)
            return false;
        final String blockIdentifier = reg.getIdentifier();

        provider.registerModels(modelRegistry);
        Map<ResourceLocation, IModelIdentifier> inverseMap = new HashMap<>();

        for(IModelIdentifier model : provider.getValidModels()){
            ResourceLocation resource = new ResourceLocation(Blacksmith.MOD_ID, blockIdentifier);
            reg.addModel(model, resource);
            inverseMap.put(resource, model);
            locationToIdentifier.put(new ResourceLocation(resource.getResourceDomain(), "models/block/"+resource.getResourcePath()+"/"+model.getModelName()), model);
        }

        //block
        StateMapperBase stateMapper = new StateMapperBase() {

            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                IModelIdentifier identifier = provider.getModelForVariant(MCInterface.fromIBlockState(state));
                String stateName = getPropertyString(state.getProperties());
                return new ModelResourceLocation(reg.getResourceLocation(identifier), stateName);
            }
        };

        for(Map.Entry<IBlockState, ModelResourceLocation> e : stateMapper.putStateModelLocations(reg.getBlock()).entrySet()){
            IModelIdentifier identifier = inverseMap.get(new ResourceLocation(e.getValue().getResourceDomain(), e.getValue().getResourcePath()));
            reg.addJsonState(new ModelResourceLocation(e.getValue().getResourceDomain()+":"+e.getValue().getResourcePath()+"/"+identifier.getModelName(), e.getValue().getVariant()));
        }

        ModelLoader.setCustomStateMapper(reg.getBlock(), stateMapper);

        //itemblock
        IModelIdentifier itemBlockModel = provider.getModelForVariant(MCInterface.fromIBlockState(reg.getBlock().getDefaultState()));
        String itemIdentifier = reg.getItemBlock().getUnlocalizedName();

        final ResourceLocation resource = new ResourceLocation(Blacksmith.MOD_ID, itemIdentifier+"/"+itemBlockModel.getModelName());
        ModelLoader.addVariantName(reg.getItemBlock(), resource.toString());
        locationToIdentifier.put(new ResourceLocation(resource.getResourceDomain(), "models/item/"+resource.getResourcePath()), itemBlockModel);

        ModelLoader.setCustomMeshDefinition(reg.getItemBlock(), new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                return new ModelResourceLocation(resource, "inventory");
            }
        });
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

        if(provider == null)
            throw new NullPointerException("Invalid IItemModelProvider: null");

        final ItemRegistry.RegisteredItem reg = ItemRegistry.INSTANCE.getRegistrationData(def);

        if (reg == null)
            return false;

        provider.registerModels(modelRegistry);

        String itemIdentifier = reg.getIdentifier();

        List<IModelIdentifier> identifiers = provider.getValidModels();
        if(identifiers == null)
            throw new NullPointerException("Invalid IItemModelProvider.getValidModels(): null");
        for(IModelIdentifier model : identifiers){
            ResourceLocation resource = new ResourceLocation(Blacksmith.MOD_ID, itemIdentifier+"/"+model.getModelName());
            ModelLoader.addVariantName(reg.getItem(), resource.toString());
            reg.addModel(model, new ModelResourceLocation(resource, "inventory"));
            locationToIdentifier.put(new ResourceLocation(resource.getResourceDomain(), "models/item/"+resource.getResourcePath()), model);
        }

        ModelLoader.setCustomMeshDefinition(reg.getItem(), new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                IModelIdentifier identifier = provider.getModelForVariant(MCInterface.fromItemStack(stack));
                return reg.getModelResourceLocation(identifier);
            }
        });

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
    public IModelRegistry getModelRegistry() {
        return modelRegistry;
    }

    public ITileEntityRenderer getTileEntityRenderer(Class<? extends ITileEntityDefinition> def) {
        return tileRenders.get(def);
    }

    public Set<String> getRegisteredDomains() {
        HashSet<String> domains = new HashSet<>();
        domains.add(Blacksmith.MOD_ID);
        return domains;
    }

    public IModel getModel(ResourceLocation modelLocation) {
        IModelIdentifier identifier = locationToIdentifier.get(modelLocation);
        return modelRegistry.getModel(identifier);
    }

    public boolean hasModelForLocation(ResourceLocation modelLocation) {
        return locationToIdentifier.containsKey(modelLocation);
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
