package net.darkaqua.blacksmith.api.block.variants;

import net.darkaqua.blacksmith.api.block.IBlock;

import java.util.Set;

/**
 * Created by cout970 on 15/01/2016.
 */
public interface IBlockData {

    IBlock getBlock();

    IBlockAttributeValue getValue(IBlockAttribute attr);

    IBlockData setValue(IBlockAttribute attr, IBlockAttributeValue value);

    Set<IBlockAttribute> getAttributes();

    IBlockData getCycleValue(IBlockAttribute attr);

    Object getInternalBlockState();
}
