package net.darkaqua.blacksmith.api.block.properties;

import java.util.Random;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IIBlockState;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.api.world.IWorld;

public interface IBlockTickHandler {

	IBlock getBlock();
	
	boolean needsRandomTick();
	
	void randomTick(WorldRef ref, IIBlockState state, Random rand);
	
	void updateTick(WorldRef ref, IIBlockState state, Random rand);

	int tickRate(IWorld world);
}
