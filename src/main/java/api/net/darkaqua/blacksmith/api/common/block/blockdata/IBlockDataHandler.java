package net.darkaqua.blacksmith.api.common.block.blockdata;

import net.darkaqua.blacksmith.api.common.block.IBlock;

import java.util.List;
import java.util.Set;

/**
 * Created by cout970 on 22/02/2016.
 */
public interface IBlockDataHandler {

    IBlock getBlock();

    IBlockData getDefaultBlockData();

    List<IBlockData> getAllStates();

    Set<IBlockAttribute> getAttributes();

    <T extends Comparable<T>> IBlockData setValue(IBlockData data, IBlockAttribute<T> attr, T value);

    <T extends Comparable<T>> IBlockData cycleValue(IBlockData data, IBlockAttribute<T> attr);
}
