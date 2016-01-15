package net.darkaqua.blacksmith.mod.item;

import net.darkaqua.blacksmith.api.block.IBlockDefinition;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

/**
 * Created by cout970 on 08/11/2015.
 */
public class BS_ItemBlock extends ItemBlock {

    private IBlockDefinition definition;

    public BS_ItemBlock(Block block) {
        super(block);
    }

    public void setBlockDefinition(IBlockDefinition def) {
        definition = def;
    }

    public IBlockDefinition getBlockDefinition() {
        return definition;
    }
}
