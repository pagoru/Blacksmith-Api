package net.darkaqua.blacksmith.api.block.properties;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IIBlockState;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.world.IWorld;

public interface IBlockTileEntityHandler {

	IBlock getBlock();
	
	boolean canProvideTileEntity(IIBlockState state);
	
	ITileEntity createTileEntity(IWorld world, IIBlockState state);
}
