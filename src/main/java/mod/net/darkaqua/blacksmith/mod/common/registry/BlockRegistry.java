package net.darkaqua.blacksmith.mod.common.registry;

import net.darkaqua.blacksmith.api.common.block.IBlock;
import net.darkaqua.blacksmith.api.common.block.IBlockContainerDefinition;
import net.darkaqua.blacksmith.api.common.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.common.registry.IBlockRegistry;
import net.darkaqua.blacksmith.mod.common.block.BS_Block;
import net.darkaqua.blacksmith.mod.common.block.BS_BlockContainer;
import net.darkaqua.blacksmith.mod.common.exceptions.BlacksmithInternalException;
import net.darkaqua.blacksmith.mod.common.item.BS_ItemBlock;
import net.darkaqua.blacksmith.mod.common.modloader.BlacksmithModContainer;
import net.darkaqua.blacksmith.mod.common.modloader.ModLoaderManager;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.*;

/**
 * Created by cout970 on 08/11/2015.
 */
public class BlockRegistry implements IBlockRegistry {

    public static final BlockRegistry INSTANCE = new BlockRegistry();

    private static final Map<IBlockDefinition, RegisteredBlock> registeredBlocks = new HashMap<>();

    private BlockRegistry() {
    }

    @Override
    public IBlock registerBlockDefinition(IBlockDefinition definition, String identifier) {
        if (ModLoaderManager.getLoadingState() != ModLoaderManager.LoadingState.PREINIT) {
            throw new IllegalStateException("Block definitions should be registered only in preInit");
        }
        if (definition == null)
            throw new NullPointerException("BlockRegistry cannot use a null IBlockDefinition to newCreativeTab a new block");
        if (identifier == null)
            throw new NullPointerException("BlockRegistry cannot use a null identifier to newCreativeTab a new block");
        BlacksmithModContainer mod = ModLoaderManager.getActiveMod();
        if (mod == null)
            throw new BlacksmithInternalException("Invalid mod container in item registration: null");

        Block result;
        identifier = mod.getModId().toLowerCase() + "/" + identifier;
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

        RegisteredBlock reg = new RegisteredBlock(definition, iblock, item, result, mod.getModId(), identifier);
        registeredBlocks.put(definition, reg);

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
        return new LinkedList<>(registeredBlocks.keySet());
    }

    @Override
    public IBlock findBlock(String domain, String name) {
        Block i = GameRegistry.findBlock(domain, name);
        if (i == null) {
            i = Block.blockRegistry.getObject(new ResourceLocation(domain, name));
        }
        return MCInterface.fromBlock(i);
    }

    @Override
    public String getBlockDomain(IBlock block) {
        GameRegistry.UniqueIdentifier id = GameRegistry.findUniqueIdentifierFor(MCInterface.toBlock(block));
        return id.modId;
    }

    @Override
    public String getBlockName(IBlock block) {
        GameRegistry.UniqueIdentifier id = GameRegistry.findUniqueIdentifierFor(MCInterface.toBlock(block));
        return id.name;
    }

    public IBlock getBlockFromDefinition(IBlockDefinition def) {
        RegisteredBlock reg = registeredBlocks.get(def);
        if (reg == null) return null;
        return reg.getIBlock();
    }

    public RegisteredBlock getRegistrationData(IBlockDefinition def) {
        return registeredBlocks.get(def);
    }

    public Collection<RegisteredBlock> getAllRegisteredBlocks() {
        return registeredBlocks.values();
    }

    public static class RegisteredBlock {

        private IBlockDefinition definition;
        private IBlock block;
        private ItemBlock itemBlock;
        private Block mcBlock;
        private String identifier;
        private String modID;

        public RegisteredBlock(IBlockDefinition definition, IBlock block, ItemBlock itemBlock, Block mcBlock, String modID, String identifier) {
            this.definition = definition;
            this.block = block;
            this.itemBlock = itemBlock;
            this.mcBlock = mcBlock;
            this.identifier = identifier;
            this.modID = modID;
        }

        public IBlockDefinition getDefinition() {
            return definition;
        }

        public IBlock getIBlock() {
            return block;
        }

        public ItemBlock getItemBlock() {
            return itemBlock;
        }

        public Block getBlock() {
            return mcBlock;
        }

        public String getIdentifier() {
            return identifier;
        }

        public String getModID() {
            return modID;
        }
    }
}
