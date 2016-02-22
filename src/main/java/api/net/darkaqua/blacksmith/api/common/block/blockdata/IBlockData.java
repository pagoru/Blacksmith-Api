package net.darkaqua.blacksmith.api.common.block.blockdata;

import net.darkaqua.blacksmith.api.common.block.IBlock;

/**
 * Created by cout970 on 15/01/2016.
 */
public interface IBlockData {

    IBlock getBlock();

    IBlockDataHandler getBlockDataHandler();

    <T extends IBlockAttributeValue<T>> T getValue(IBlockAttribute<T> attr);

    Object getInternalBlockState();
}
