package net.darkaqua.blacksmith.mod.registry;

import net.darkaqua.blacksmith.api.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.item.IItemDefinition;
import net.darkaqua.blacksmith.api.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.registry.IRenderRegistry;
import net.darkaqua.blacksmith.api.render.model.IBlockModelProvider;
import net.darkaqua.blacksmith.api.render.model.IItemModelProvider;
import net.darkaqua.blacksmith.api.render.model.IRenderModel;
import net.darkaqua.blacksmith.api.render.tileentity.ITileEntityRenderer;
import net.darkaqua.blacksmith.api.tileentity.ITileEntityDefinition;
import net.darkaqua.blacksmith.mod.exceptions.BlacksmithInternalException;
import net.darkaqua.blacksmith.mod.modloader.BlacksmithModContainer;
import net.darkaqua.blacksmith.mod.modloader.ModLoaderManager;
import net.darkaqua.blacksmith.mod.render.model.BakedBlockModel;
import net.darkaqua.blacksmith.mod.render.model.BakedItemModel;
import net.darkaqua.blacksmith.mod.render.model.ItemBlockModelProvider;
import net.darkaqua.blacksmith.mod.render.model.RenderModelWrapper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Created by cout970 on 07/12/2015.
 */
public class RenderRegistry implements IRenderRegistry {

    public static final RenderRegistry INSTANCE = new RenderRegistry();
    private static ModelRegistry modelRegistry = ModelRegistry.INSTANCE;
    private static Map<Class<? extends ITileEntityDefinition>, ITileEntityRenderer> tileRenders = new IdentityHashMap<>();

    private static Map<ModelResourceLocation, IBakedModel> locationToBakedModel = new HashMap<>();
    private static Map<Block, IBlockModelProvider> registeredBlockModelProviders = new HashMap<>();
    private static Map<Item, IItemModelProvider> registeredItemModelProviders = new HashMap<>();
    private static Map<IRenderModel, IBakedModel> modelCache = new HashMap<>();

    private RenderRegistry() {}

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

        //Block
        ModelLoader.setCustomStateMapper(reg.getBlock(), new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return new ModelResourceLocation(Block.blockRegistry.getNameForObject(state.getBlock()), "normal");
            }
        });

        locationToBakedModel.put(new ModelResourceLocation(Block.blockRegistry.getNameForObject(reg.getBlock()), "normal"), new BakedBlockModel(reg.getBlock()));
        registeredBlockModelProviders.put(reg.getBlock(), provider);
        provider.registerModels(modelRegistry);


        //Item
        ModelLoader.setCustomMeshDefinition(reg.getItemBlock(), new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                return new ModelResourceLocation(Item.itemRegistry.getNameForObject(stack.getItem()), "inventory");
            }
        });

        locationToBakedModel.put(new ModelResourceLocation(Item.itemRegistry.getNameForObject(reg.getItemBlock()), "inventory"), new BakedItemModel());
        registeredItemModelProviders.put(reg.getItemBlock(), new ItemBlockModelProvider(provider));

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

        ItemRegistry.RegisteredItem reg = ItemRegistry.INSTANCE.getRegistrationData(def);

        if (reg == null)
            return false;

        ModelLoader.setCustomMeshDefinition(reg.getItem(), new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                return new ModelResourceLocation(Item.itemRegistry.getNameForObject(stack.getItem()), "normal");
            }
        });

        locationToBakedModel.put(new ModelResourceLocation(Item.itemRegistry.getNameForObject(reg.getItem()), "normal"), new BakedItemModel());
        registeredItemModelProviders.put(reg.getItem(), provider);
        provider.registerModels(modelRegistry);
        return true;
    }

    @Override
    public boolean registerTileEntityRenderer(Class<? extends ITileEntityDefinition> def, ITileEntityRenderer<? extends ITileEntityDefinition> renderer) {
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

    @SubscribeEvent
    public void onBakedEvent(ModelBakeEvent event){
        for(Map.Entry<ModelResourceLocation, IBakedModel> e : locationToBakedModel.entrySet()){
            event.modelRegistry.putObject(e.getKey(), e.getValue());
        }
        ModelRegistry.bakeModels();
    }

    public IBlockModelProvider getBlockModelProvider(Block block) {
        return registeredBlockModelProviders.get(block);
    }

    public IItemModelProvider getItemModelProvider(Item item) {
        return registeredItemModelProviders.get(item);
    }

    public IBakedModel getBakedModel(IRenderModel id) {
        if (id == null)return null;
        if(!modelCache.containsKey(id)){
            modelCache.put(id, new RenderModelWrapper(id));
        }
        return modelCache.get(id);
    }
}
