package net.darkaqua.blacksmith.api.block.properties.base;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.properties.IBlockTickHandler;

public class SimpleBlockTickHandler implements IBlockTickHandler{

	protected IBlock block;
	
	public SimpleBlockTickHandler(IBlock block) {
		this.block = block;
	}
	
	@Override
	public IBlock getBlock() {
		return block;
	}

}
