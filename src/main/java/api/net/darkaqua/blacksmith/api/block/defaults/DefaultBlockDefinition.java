package net.darkaqua.blacksmith.api.block.defaults;

import net.darkaqua.blacksmith.api.block.BlockMaterialFactory;
import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.block.IBlockMaterial;
import net.darkaqua.blacksmith.api.creativetab.CreativeTabFactory;
import net.darkaqua.blacksmith.api.creativetab.ICreativeTab;

/**
 * Created by cout970 on 08/11/2015.
 */
public class DefaultBlockDefinition implements IBlockDefinition {

    protected String blockName;
    protected IBlock parent;

    public DefaultBlockDefinition(String name) {
        blockName = name;
    }

    public DefaultBlockDefinition() {
        blockName = getClass().getName();
    }

    @Override
    public void onCreate(IBlock block) {
        parent = block;
    }

    @Override
    public IBlockMaterial getBlockMaterial() {
        return BlockMaterialFactory.IRON;
    }

    @Override
    public String getUnlocalizedName() {
        return blockName;
    }

    @Override
    public ICreativeTab getCreativeTab() {
        return CreativeTabFactory.BLOCKS_TAB;
    }

    @Override
    public IBlock getBlock() {
        return parent;
    }
}
