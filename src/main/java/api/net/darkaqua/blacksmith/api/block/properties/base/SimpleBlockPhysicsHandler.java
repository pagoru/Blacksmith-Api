package net.darkaqua.blacksmith.api.block.properties.base;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockState;
import net.darkaqua.blacksmith.api.block.properties.IBlockPhysicsHandler;
import net.darkaqua.blacksmith.api.entity.Entity;
import net.darkaqua.blacksmith.api.util.BlockPos;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.world.IBlockAccess;
import net.darkaqua.blacksmith.api.world.World;

public class SimpleBlockPhysicsHandler implements IBlockPhysicsHandler{

	protected Cube blockBounds;
	protected IBlock block;
	
	public SimpleBlockPhysicsHandler(IBlock block) {
		this.block = block;
		blockBounds = Cube.fullBlock();
	}

	@Override
	public IBlock getBlock() {
		return block;
	}

	@Override
	public boolean isAir(IBlockAccess world, BlockPos pos) {
		return false;
	}

	@Override
	public boolean isSolidBlock() {
		return true;
	}

	@Override
	public boolean isNormalBlock() {
		return true;
	}

	@Override
	public boolean isFullBlock() {
		return true;
	}

	@Override
	public boolean isCompleteBlock() {
		return true;
	}

	@Override
	public boolean isSideSolid(IBlockAccess world, BlockPos pos, Direction side) {
		return true;
	}

	@Override
	public boolean isTraspasable(IBlockAccess world, BlockPos pos) {
		return false;
	}

	@Override
	public boolean canCollide(IBlockState state, boolean hitIfIsLiquid) {
		return true;
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {}

	@Override
	public void setBlockBounds(Cube blockBounds) {
		this.blockBounds = blockBounds.copy();
	}

	@Override
	public Cube getBlockBounds() {
		return blockBounds;
	}

	@Override
	public Cube getColisionBox() {
		return blockBounds;
	}

	@Override
	public Cube getSelectionBox() {
		return blockBounds;
	}

}
