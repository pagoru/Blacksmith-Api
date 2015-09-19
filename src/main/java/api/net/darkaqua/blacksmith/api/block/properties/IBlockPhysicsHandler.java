package net.darkaqua.blacksmith.api.block.properties;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockState;
import net.darkaqua.blacksmith.api.entity.Entity;
import net.darkaqua.blacksmith.api.util.BlockPos;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.world.IBlockAccess;
import net.darkaqua.blacksmith.api.world.World;

public interface IBlockPhysicsHandler {

	IBlock getBlock();
	
	boolean isAir(IBlockAccess world, BlockPos pos);
	
	boolean isSolidBlock();
	
	boolean isNormalBlock();
	
	boolean isFullBlock();
	
	boolean isCompleteBlock();
	
	boolean isSideSolid(IBlockAccess world, BlockPos pos, Direction side);
	
	boolean isTraspasable(IBlockAccess world, BlockPos pos);
	
	boolean canCollide(IBlockState state, boolean hitIfIsLiquid);
	
	void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity);
	
	void setBlockBounds(Cube blockBounds);
	
	Cube getBlockBounds();
	
	Cube getColisionBox();
	
	Cube getSelectionBox();
}
