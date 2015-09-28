package net.darkaqua.blacksmith.mod.util;

import net.darkaqua.blacksmith.api.util.BlockLoc;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.util.Direction;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MCInterface {

	public static IBlockState toBlockState(net.darkaqua.blacksmith.api.block.IIBlockState stateFromMeta) {
		return null;
	}

	public static net.darkaqua.blacksmith.api.block.IIBlockState fromBlockState(IBlockState state) {
		return null;
	}

	public static net.darkaqua.blacksmith.api.world.IBlockAccess fromBlockAccess(IBlockAccess worldIn) {
		return null;
	}

	public static BlockLoc fromBlockPos(BlockPos pos) {
		return new BlockLoc(pos.getX(), pos.getY(), pos.getZ());
	}

	public static net.darkaqua.blacksmith.api.world.IWorld fromWorld(World worldIn) {
		return null;
	}

	public static Direction fromEnumFacing(EnumFacing side) {
		if (side == null)
			return null;
		return Direction.getDirection(side.getIndex());
	}

	public static AxisAlignedBB toAxisAlignedBB(Cube cube) {
		return AxisAlignedBB.fromBounds(cube.minX(), cube.minY(), cube.minZ(), cube.maxX(), cube.maxY(),
				cube.maxZ());
	}

	public static Cube fromAxisAlignedBB(AxisAlignedBB aabb) {
		return new Cube(aabb.minX, aabb.minY, aabb.minZ, aabb.maxX, aabb.maxY, aabb.maxZ);
	}

	public static net.darkaqua.blacksmith.api.entity.IEntity fromEntity(Entity entity) {
		return null;
	}

}
