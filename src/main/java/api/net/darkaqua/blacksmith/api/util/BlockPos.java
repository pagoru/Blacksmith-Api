package net.darkaqua.blacksmith.api.util;

public class BlockPos extends Vector3i{

	public BlockPos(int x, int y, int z) {
		super(x, y, z);
	}
	
	public BlockPos copy() {
		return new BlockPos(x, y, z);
	}
}
