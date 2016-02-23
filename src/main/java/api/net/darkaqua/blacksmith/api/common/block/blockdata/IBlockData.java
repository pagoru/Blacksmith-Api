package net.darkaqua.blacksmith.api.common.block.blockdata;

import net.darkaqua.blacksmith.api.common.block.IBlock;

import java.util.Set;

/**
 * Created by cout970 on 15/01/2016.
 */
public interface IBlockData {

    IBlock getBlock();

    IBlockDataHandler getBlockDataHandler();

    <T extends Comparable<T>> T getValue(IBlockAttribute<T> attr);

    default <T extends Comparable<T>> IBlockData setValue(IBlockAttribute<T> attr, T value) {
        return getBlockDataHandler().setValue(this, attr, value);
    }

    default Set<IBlockAttribute> getAttributes() {
        return getBlockDataHandler().getAttributes();
    }

    Object getInternalBlockState();
}
