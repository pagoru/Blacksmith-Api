package net.darkaqua.blacksmith.api.common.block.blockdata;

import net.darkaqua.blacksmith.api.common.block.IBlock;

import java.util.List;
import java.util.Set;

/**
 * Created by cout970 on 15/01/2016.
 */
public interface IBlockDataGenerator {

    List<IBlockData> getAllStates();

    IBlockData getDefaultData();

    IBlock getBlock();

    Set<IBlockAttribute> getAttributes();
}
