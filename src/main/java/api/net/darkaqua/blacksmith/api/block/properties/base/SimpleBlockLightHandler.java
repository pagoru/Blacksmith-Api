package net.darkaqua.blacksmith.api.block.properties.base;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.properties.IBlockLightHandler;
import net.darkaqua.blacksmith.api.util.BlockLoc;
import net.darkaqua.blacksmith.api.world.IBlockAccess;

public class SimpleBlockLightHandler implements IBlockLightHandler{

	protected IBlock block;
	
	public SimpleBlockLightHandler(IBlock block){
		this.block = block;
	}
	
	@Override
	public IBlock getBlock() {
		return block;
	}

	@Override
	public float getLightOpacity() {
		return 255;
	}

	@Override
	public float getLightOpacity(IBlockAccess world, BlockLoc pos) {
		return getLightOpacity();
	}

	@Override
	public float getLightEmited() {
		return 0;
	}

	@Override
	public boolean isOpaque() {
		return true;
	}

}
