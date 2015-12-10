package net.darkaqua.blacksmith.mod.registry;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockContainerDefinition;
import net.darkaqua.blacksmith.api.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.registry.IBlockRegistry;
import net.darkaqua.blacksmith.api.render.model.json.IJsonModelWrapper;
import net.darkaqua.blacksmith.mod.block.BS_Block;
import net.darkaqua.blacksmith.mod.block.BS_BlockContainer;
import net.darkaqua.blacksmith.mod.item.BS_ItemBlock;
import net.darkaqua.blacksmith.mod.modloader.BlacksmithModContainer;
import net.darkaqua.blacksmith.mod.modloader.ModLoaderManager;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.Block;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
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

    private static final Map<IBlockDefinition, RegisteredBlock> registeredblocks = new HashMap<>();

    private BlockRegistry() {}

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
        return new LinkedList<>(registeredblocks.keySet());
    }

    @Override
    public IBlock findBlock(String domain, String name) {
        Block i = GameRegistry.findBlock(domain, name);
        if (i == null) {
            i = (net.minecraft.block.Block) net.minecraft.block.Block.blockRegistry.getObject(new ResourceLocation(domain, name));
        }
        return MCInterface.fromBlock(i);
    }

    @Override
    public IBlock registerBlockDefinition(IBlockDefinition definition, String identifier) {
        BlacksmithModContainer mod = ModLoaderManager.getActiveMod();
        if (mod == null) {
            throw new IllegalStateException("Block models should be registered only in preInit");
        }
        if (definition == null)
            throw new NullPointerException("BlockRegistry cannot use a null IBlockDefinition to create a new block");
        if (identifier == null)
            throw new NullPointerException("BlockRegistry cannot use a null identifier to create a new block");

        Block result;
        //creating and registering the block
        if (definition instanceof IBlockContainerDefinition) {
            BS_BlockContainer block = new BS_BlockContainer((IBlockContainerDefinition) definition);
            result = GameRegistry.registerBlock(block, BS_ItemBlock.class, identifier);
        } else {
            BS_Block block = new BS_Block(definition);
            result = GameRegistry.registerBlock(block, BS_ItemBlock.class, identifier);
        }


        IBlock iblock = MCInterface.fromBlock(result);
        BS_ItemBlock item = (BS_ItemBlock) Item.getItemFromBlock(result);
        //setting up the itemblock
        item.setBlockDefinition(definition);

        RegisteredBlock reg = new RegisteredBlock(definition, iblock, item, result, identifier);
        registeredblocks.put(definition, reg);

        return iblock;
    }

    public IBlock getBlockFromDefinition(IBlockDefinition def){
        RegisteredBlock reg = registeredblocks.get(def);
        if(reg == null)return null;
        return reg.getBlock();
    }

    public RegisteredBlock getRegistrationData(IBlockDefinition def) {
        return registeredblocks.get(def);
    }

    public static class RegisteredBlock{

        private IBlockDefinition definition;
        private IBlock block;
        private ItemBlock itemBlock;
        private Block mcBlock;
        private String identifier;
        private Map<ModelResourceLocation,List<IJsonModelWrapper>> renderMap;

        public RegisteredBlock(IBlockDefinition definition, IBlock block, ItemBlock itemBlock, Block mcBlock, String identifier) {
            this.definition = definition;
            this.block = block;
            this.itemBlock = itemBlock;
            this.mcBlock = mcBlock;
            this.identifier = identifier;
        }

        public IBlockDefinition getDefinition() {
            return definition;
        }

        public IBlock getBlock() {
            return block;
        }

        public ItemBlock getItemBlock() {
            return itemBlock;
        }

        public Block getMcBlock() {
            return mcBlock;
        }

        public String getIdentifier() {
            return identifier;
        }

        public void setRenderMap(Map<ModelResourceLocation,List<IJsonModelWrapper>> renderMap) {
            this.renderMap = renderMap;
        }

        public Map<ModelResourceLocation, List<IJsonModelWrapper>> getRenderMap() {
            return renderMap;
        }
    }
}
