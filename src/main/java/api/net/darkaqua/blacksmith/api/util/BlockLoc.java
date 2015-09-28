package net.darkaqua.blacksmith.api.util;

public class BlockLoc extends Vector3i{

	public BlockLoc(int x, int y, int z) {
		super(x, y, z);
	}
	
	public BlockLoc copy() {
		return new BlockLoc(x, y, z);
	}
}
