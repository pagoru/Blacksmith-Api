package net.darkaqua.blacksmith.api.block.properties;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockState;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.world.World;

public interface IBlockTileEntityHandler {

	IBlock getBlock();
	
	boolean canProvideTileEntity(IBlockState state);
	
	ITileEntity createTileEntity(World world, IBlockState state);
}
