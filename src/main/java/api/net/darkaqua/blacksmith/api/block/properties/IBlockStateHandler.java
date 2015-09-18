package net.darkaqua.blacksmith.api.block.properties;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IWorldAccess;

public interface IBlockStateHandler {
	
	IBlock getBlock();
	
	IBlockState getDefaultState();

	IBlockState getActualState(IBlockState state, IWorldAccess worldIn, BlockPos pos);
	
	IBlockState getStateFromMeta(int meta);
	
	int getMetaFromState(IBlockState state);

	IBlockState getStateForEntityRender(IBlockState state);
}
