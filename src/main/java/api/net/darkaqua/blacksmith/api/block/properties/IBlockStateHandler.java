package net.darkaqua.blacksmith.api.block.properties;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockState;
import net.darkaqua.blacksmith.api.util.BlockPos;
import net.darkaqua.blacksmith.api.world.IWorldAccess;

public interface IBlockStateHandler {
	
	IBlock getBlock();
	
	IBlockState getDefaultState();

	IBlockState getActualState(IBlockState state, IWorldAccess worldIn, BlockPos pos);
	
	IBlockState getStateFromMeta(int meta);
	
	int getMetaFromState(IBlockState state);
}
