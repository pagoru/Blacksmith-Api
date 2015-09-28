package net.darkaqua.blacksmith.api.block.properties.base;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IIBlockState;
import net.darkaqua.blacksmith.api.block.SimpleBlockState;
import net.darkaqua.blacksmith.api.block.properties.IBlockStateHandler;
import net.darkaqua.blacksmith.api.util.BlockLoc;
import net.darkaqua.blacksmith.api.world.IBlockAccess;

public class SimpleBlockStateHandler implements IBlockStateHandler{

	protected IBlock block;
	protected IIBlockState blockState;

	public SimpleBlockStateHandler(IBlock block){
		this.block = block;
		blockState = new SimpleBlockState(block);
	}
	
	@Override
	public IBlock getBlock() {
		return block;
	}

	@Override
	public IIBlockState getDefaultState() {
		return blockState;
	}

	@Override
	public IIBlockState getActualState(IIBlockState state, IBlockAccess worldIn, BlockLoc pos) {
		return state;
	}

	@Override
	public IIBlockState getStateFromMeta(int meta) {
		return blockState;
	}

	@Override
	public int getMetaFromState(IIBlockState state) {
		return 0;
	}

	
}
