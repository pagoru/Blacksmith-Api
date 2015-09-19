package net.darkaqua.blacksmith.api.block.properties.base;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockState;
import net.darkaqua.blacksmith.api.block.SimpleBlockState;
import net.darkaqua.blacksmith.api.block.properties.IBlockStateHandler;
import net.darkaqua.blacksmith.api.util.BlockPos;
import net.darkaqua.blacksmith.api.world.IWorldAccess;

public class SimpleBlockStateHandler implements IBlockStateHandler{

	protected IBlock block;
	protected IBlockState blockState;

	public SimpleBlockStateHandler(IBlock block){
		this.block = block;
		blockState = new SimpleBlockState(block);
	}
	
	@Override
	public IBlock getBlock() {
		return block;
	}

	@Override
	public IBlockState getDefaultState() {
		return blockState;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IWorldAccess worldIn, BlockPos pos) {
		return state;
	}
	
	@Override
	public IBlockState getStateForEntityRender(IBlockState state) {
		return state;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return blockState;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}
}
