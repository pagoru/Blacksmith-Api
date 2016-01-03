package net.darkaqua.blacksmith.api.util;

import net.darkaqua.blacksmith.api.block.IBlockVariant;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.world.IWorld;

public class WorldRef {

	private IWorld world;
	private Vect3i position;
	
	public WorldRef(IWorld world, Vect3i position) {
		this.world = world;
		this.position = position.copy();
	}

	public IWorld getWorld() {
		return world;
	}

	public void setWorld(IWorld world) {
		this.world = world;
	}

	public Vect3i getPosition() {
		return position;
	}

	public void setPosition(Vect3i position) {
		this.position = position;
	}

	public WorldRef copy(){
		return new WorldRef(world, position);
	}

	public WorldRef move(Vect3i dir){
		WorldRef ref = copy();
		ref.getPosition().add(dir);
		return ref;
	}

	public WorldRef move(Direction dir){
		WorldRef ref = copy();
		ref.getPosition().add(dir);
		return ref;
	}

	public IBlockVariant getBlockVariant() {
		return world.getBlockVariant(position);
	}

	public void setBlockVariant(IBlockVariant variant, int flags) {
		world.setBlockVariant(variant, position, flags);
	}

	public void setBlockVariant(IBlockVariant variant) {
		world.setBlockVariant(variant, position, 3);
	}

	public ITileEntity getTileEntity() {
		return world.getTileEntity(position);
	}
}
