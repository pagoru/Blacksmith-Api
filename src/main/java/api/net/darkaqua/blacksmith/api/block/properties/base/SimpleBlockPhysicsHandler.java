package net.darkaqua.blacksmith.api.block.properties.base;

import java.util.ArrayList;
import java.util.List;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IIBlockState;
import net.darkaqua.blacksmith.api.block.properties.IBlockPhysicsHandler;
import net.darkaqua.blacksmith.api.entity.IEntity;
import net.darkaqua.blacksmith.api.util.BlockLoc;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.world.IBlockAccess;
import net.darkaqua.blacksmith.api.world.IWorld;

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
	public boolean isAir(IBlockAccess world, BlockLoc pos) {
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
	public boolean isSideSolid(IBlockAccess world, BlockLoc pos, Direction side) {
		return true;
	}

	@Override
	public boolean isTraspasable(IBlockAccess world, BlockLoc pos) {
		return false;
	}

	@Override
	public boolean canCollide(IIBlockState state, boolean hitIfIsLiquid) {
		return true;
	}

	@Override
	public void setBlockBounds(Cube blockBounds) {
		this.blockBounds = blockBounds.copy();
	}

	@Override
	public Cube getBlockBounds() {
		return blockBounds;
	}

	@Override
	public Cube getColisionBox(IWorld world, BlockLoc pos, IIBlockState state) {
		return blockBounds.translate(pos.toVector3d());
	}
	
	@Override
	public Cube getSelectionBox(IWorld world, BlockLoc pos) {
		return getBlock().getPhysicsHandler().getBlockBounds().translate(pos.toVector3d());
	}

	@Override
	public List<Cube> getCollidingCubes(IWorld world, BlockLoc pos, IIBlockState state, Cube mask, IEntity entity) {
		List<Cube> list = new ArrayList<Cube>();
		list.add(getColisionBox(world, pos, state));
		return list;
	}
}
