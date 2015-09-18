package net.darkaqua.blacksmith.api.block.properties;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.util.BlockPos;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.world.IBlockAccess;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public interface IBlockPhysicsHandler {

	IBlock getBlock();
	
	default boolean isAir(IBlockAccess world, BlockPos pos){return false;};
	
	default boolean isSolidBlock(){return true;};
	
	default boolean isNormalBlock(){return true;};
	
	default boolean isFullBlock(){return true;};
	
	default boolean isCompleteBlock(){return true;};
	
	default boolean isSideSolid(IBlockAccess world, BlockPos pos, Direction side){return true;};
	
	default boolean isTraspasable(IBlockAccess world, BlockPos pos){return false;};
	
	default boolean canCollide(IBlockState state, boolean hitIfIsLiquid){return true;};
	
	default void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn){};
	
	default void setBlockBounds(Cube boundingBox){};
	
	default Cube getBlockBounds(){return Cube.fullBlock();};
	
	default Cube getColisionBox(){return Cube.fullBlock();};
	
	default Cube getSelectionBox(){return Cube.fullBlock();};
}
