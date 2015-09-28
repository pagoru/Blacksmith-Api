package net.darkaqua.blacksmith.api.block.properties;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IIBlockState;
import net.darkaqua.blacksmith.api.util.BlockLoc;
import net.darkaqua.blacksmith.api.world.IBlockAccess;

public interface IBlockStateHandler {
	
	IBlock getBlock();
	
	IIBlockState getDefaultState();

	IIBlockState getActualState(IIBlockState state, IBlockAccess worldIn, BlockLoc pos);
	
	IIBlockState getStateFromMeta(int meta);
	
	int getMetaFromState(IIBlockState state);
}
