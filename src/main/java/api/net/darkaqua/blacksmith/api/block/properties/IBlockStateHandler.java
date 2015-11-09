package net.darkaqua.blacksmith.api.block.properties;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IIBlockState;
import net.darkaqua.blacksmith.api.util.Vector3i;
import net.darkaqua.blacksmith.api.world.IIBlockAccess;

public interface IBlockStateHandler {
	
	IBlock getBlock();
	
	IIBlockState getDefaultState();

	IIBlockState getActualState(IIBlockState state, IIBlockAccess worldIn, Vector3i pos);
	
	IIBlockState getStateFromMeta(int meta);
	
	int getMetaFromState(IIBlockState state);
}
