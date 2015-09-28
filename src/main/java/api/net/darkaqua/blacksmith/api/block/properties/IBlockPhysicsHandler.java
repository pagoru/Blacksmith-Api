package net.darkaqua.blacksmith.api.block.properties;

import java.util.List;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IIBlockState;
import net.darkaqua.blacksmith.api.entity.IEntity;
import net.darkaqua.blacksmith.api.util.BlockLoc;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.world.IBlockAccess;
import net.darkaqua.blacksmith.api.world.IWorld;

public interface IBlockPhysicsHandler {

	IBlock getBlock();
	
	boolean isAir(IBlockAccess world, BlockLoc pos);
	
	boolean isSolidBlock();
	
	boolean isNormalBlock();
	
	boolean isFullBlock();
	
	boolean isCompleteBlock();
	
	boolean isSideSolid(IBlockAccess world, BlockLoc pos, Direction side);
	
	boolean isTraspasable(IBlockAccess world, BlockLoc pos);
	
	boolean canCollide(IIBlockState state, boolean hitIfIsLiquid);
	
	void setBlockBounds(Cube blockBounds);
	
	Cube getBlockBounds();
	
	Cube getColisionBox(IWorld world, BlockLoc pos, IIBlockState state);

	Cube getSelectionBox(IWorld world, BlockLoc pos);
	
	List<Cube> getCollidingCubes(IWorld world, BlockLoc pos, IIBlockState state, Cube mask, IEntity entity);
}
