package net.darkaqua.blacksmith.api.block.properties;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.blockstate.IBlockVariant;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.world.IWorld;

public interface IBlockTileEntityProperties {

	IBlock getBlock();
	
	boolean canProvideTileEntity(IBlockVariant state);
	
	ITileEntity createTileEntity(IWorld world, IBlockVariant state);
}
