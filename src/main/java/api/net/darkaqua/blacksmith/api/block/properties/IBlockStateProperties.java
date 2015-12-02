package net.darkaqua.blacksmith.api.block.properties;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.blockstate.IBlockVariant;
import net.darkaqua.blacksmith.api.util.Vector3i;
import net.darkaqua.blacksmith.api.world.IIBlockAccess;

public interface IBlockStateProperties {
	
	IBlock getBlock();
	
	IBlockVariant getDefaultState();

	IBlockVariant getActualState(IBlockVariant state, IIBlockAccess worldIn, Vector3i pos);
	
	IBlockVariant getStateFromMeta(int meta);
	
	int getMetaFromState(IBlockVariant state);
}
