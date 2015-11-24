package net.darkaqua.blacksmith.mod.registry;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockContainerDefinition;
import net.darkaqua.blacksmith.api.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.registry.IBlockRegistry;
import net.darkaqua.blacksmith.api.registry.StaticAccess;
import net.darkaqua.blacksmith.mod.block.BS_Block;
import net.darkaqua.blacksmith.mod.block.BS_BlockContainer;
import net.darkaqua.blacksmith.mod.item.BS_ItemBlock;
import net.darkaqua.blacksmith.mod.render.ModelUtils;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by cout970 on 08/11/2015.
 */
public class BlockRegistry implements IBlockRegistry {

    public static final BlockRegistry INSTANCE = new BlockRegistry();

    private static final List<IBlockDefinition> blockDefinitions = new LinkedList<>();
    private static final Map<IBlockDefinition, IBlock> blocksRegistered = new HashMap<>();
    private static final List<RenderRegistration> renders = new LinkedList<>();

    private BlockRegistry() {}

    public void registerRenders(ModelBakeEvent event){
        for(RenderRegistration render : renders){
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(render.getItem(), render.getMeta(), render.getModel());
        }
    }

    @Override
    public IBlock registerBlockDefinition(IBlockDefinition definition, String identifier) {
        if (definition == null)
            throw new NullPointerException("BlockRegistry cannot use a null IBlockDefinition to create a new block");
        if (identifier == null)
            throw new NullPointerException("BlockRegistry cannot use a null identifier to create a new block");

        Block result = null;
        if(definition instanceof IBlockContainerDefinition){
            BS_BlockContainer block = new BS_BlockContainer((IBlockContainerDefinition) definition);
            result = GameRegistry.registerBlock(block, BS_ItemBlock.class, identifier);
        }else {
            BS_Block block = new BS_Block(definition);
            result = GameRegistry.registerBlock(block, BS_ItemBlock.class, identifier);
        }
        IBlock iblock = MCInterface.fromBlock(result);

        blockDefinitions.add(definition);
        blocksRegistered.put(definition, iblock);
        Item item = Item.getItemFromBlock(result);
        if(item instanceof BS_ItemBlock) {
            ((BS_ItemBlock) item).setBlockDefinition(definition);
        }
        if(StaticAccess.GAME.isClient()) {
            if (definition.getBlockRenderHandler() != null) {
                for (int i = 0; i < definition.getNumMetadataStates(); i++) {
                    RenderRegistration render = new RenderRegistration(item, i, ModelUtils.getModelResourceLocation(item, i, definition, identifier));
                    renders.add(render);
                    ModelBakery.addVariantName(render.getItem(), render.getModel().getResourceDomain()+":"+render.getModel().getResourcePath());
                }
            }
        }
        return iblock;
    }

    @Override
    public List<IBlock> getRegisteredBlocks() {
        List<IBlock> list = new LinkedList<>();

        for (Object b : GameData.getBlockRegistry()) {
            if (b instanceof Block) {
                list.add(MCInterface.fromBlock((Block) b));
            }
        }
        return list;
    }

    @Override
    public List<IBlockDefinition> getRegisteredBlockDefinitions() {
        return new LinkedList<>(blockDefinitions);
    }

    @Override
    public IBlock findBlock(String domain, String name) {
        Block i = GameRegistry.findBlock(domain, name);
        if (i == null) {
            i = (net.minecraft.block.Block) net.minecraft.block.Block.blockRegistry.getObject(new ResourceLocation(domain, name));
        }
        return MCInterface.fromBlock(i);
    }

    public IBlock getBlockFromDefinition(IBlockDefinition def){
        return blocksRegistered.get(def);
    }

    private class RenderRegistration{
        private Item item;
        private int meta;
        private ModelResourceLocation model;

        public RenderRegistration(Item item, int meta, ModelResourceLocation model) {
            this.item = item;
            this.meta = meta;
            this.model = model;
        }

        public Item getItem() {
            return item;
        }

        public int getMeta() {
            return meta;
        }

        public ModelResourceLocation getModel() {
            return model;
        }
    }
}
