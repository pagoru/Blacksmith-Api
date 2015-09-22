package net.darkaqua.blacksmith.api.world;

import net.darkaqua.blacksmith.api.block.IBlockState;
import net.darkaqua.blacksmith.api.util.BlockPos;

public interface World extends IWorldAccess {

	IBlockState getBlockState(BlockPos pos);

}
