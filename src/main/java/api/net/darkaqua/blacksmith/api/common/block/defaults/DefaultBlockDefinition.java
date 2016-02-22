package net.darkaqua.blacksmith.api.common.block.defaults;

import net.darkaqua.blacksmith.api.common.block.BlockMaterialFactory;
import net.darkaqua.blacksmith.api.common.block.IBlock;
import net.darkaqua.blacksmith.api.common.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.common.block.IBlockMaterial;
import net.darkaqua.blacksmith.api.client.creativetab.CreativeTabFactory;
import net.darkaqua.blacksmith.api.client.creativetab.ICreativeTab;

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
