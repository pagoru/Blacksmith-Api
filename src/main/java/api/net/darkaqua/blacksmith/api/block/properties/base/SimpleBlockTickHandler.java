package net.darkaqua.blacksmith.api.block.properties.base;

import java.util.Random;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IIBlockState;
import net.darkaqua.blacksmith.api.block.properties.IBlockTickHandler;
import net.darkaqua.blacksmith.api.util.BlockLoc;
import net.darkaqua.blacksmith.api.world.IWorld;

public class SimpleBlockTickHandler implements IBlockTickHandler{

	protected IBlock block;
	
	public SimpleBlockTickHandler(IBlock block) {
		this.block = block;
	}
	
	@Override
	public IBlock getBlock() {
		return block;
	}

	@Override
	public boolean needsRandomTick() {
		return false;
	}

	@Override
	public void randomTick(IWorld world, BlockLoc pos, IIBlockState state, Random rand) {		
		updateTick(world, pos, state, rand);
	}

	@Override
	public void updateTick(IWorld world, BlockLoc pos, IIBlockState state, Random rand) {}

	@Override
	public int tickRate(IWorld world) {
		return 10;
	}
}
