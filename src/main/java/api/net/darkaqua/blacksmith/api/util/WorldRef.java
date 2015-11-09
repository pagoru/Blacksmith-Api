package net.darkaqua.blacksmith.api.util;

import net.darkaqua.blacksmith.api.world.IWorld;

public class WorldRef {

	private IWorld world;
	private Vector3i position;
	
	public WorldRef(IWorld world, Vector3i position) {
		this.world = world;
		this.position = position;
	}

	public IWorld getWorld() {
		return world;
	}

	public void setWorld(IWorld world) {
		this.world = world;
	}

	public Vector3i getPosition() {
		return position;
	}

	public void setPosition(Vector3i position) {
		this.position = position;
	}
}
