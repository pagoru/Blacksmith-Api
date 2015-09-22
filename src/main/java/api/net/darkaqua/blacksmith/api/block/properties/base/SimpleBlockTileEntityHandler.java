package net.darkaqua.blacksmith.api.block.properties.base;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockState;
import net.darkaqua.blacksmith.api.block.properties.IBlockTileEntityHandler;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.world.World;

public class SimpleBlockTileEntityHandler implements IBlockTileEntityHandler{

	protected IBlock block;

	public SimpleBlockTileEntityHandler(IBlock block) {
		this.block = block;
	}

	public IBlock getBlock(){
		return block;
	}
	
	@Override
	public boolean canProvideTileEntity(IBlockState state) {
		return false;
	}

	@Override
	public ITileEntity createTileEntity(World world, IBlockState state) {
		return null;
	}

}
