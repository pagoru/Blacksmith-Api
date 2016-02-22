package net.darkaqua.blacksmith.api.common.block.blockdata;

import net.darkaqua.blacksmith.api.common.block.IBlock;

import java.util.List;
import java.util.Set;

/**
 * Created by cout970 on 22/02/2016.
 */
public interface IBlockDataHandler {

    IBlock getBlock();

    IBlockData getDefaultData();

    List<IBlockData> getAllStates();

    Set<IBlockAttribute> getAttributes();

    <T extends IBlockAttributeValue<T>> IBlockData withValue(IBlockData data, IBlockAttribute<T> attr, T value);

    <T extends IBlockAttributeValue<T>> IBlockData getCycleValue(IBlockData data, IBlockAttribute<T> attr);
}
