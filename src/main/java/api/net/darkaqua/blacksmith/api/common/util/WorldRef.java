package net.darkaqua.blacksmith.api.common.util;

import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.common.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3i;
import net.darkaqua.blacksmith.api.common.world.IWorld;

public class WorldRef {

    private final IWorld world;
    private final Vect3i position;

    public WorldRef(IWorld world, Vect3i position) {
        this.world = world;
        this.position = position.copy();
    }

    public IWorld getWorld() {
        return world;
    }

    public Vect3i getPosition() {
        return position.copy();
    }

    public WorldRef copy() {
        return new WorldRef(world, position);
    }

    public WorldRef move(Vect3i dir) {
        return new WorldRef(world, getPosition().add(dir));
    }

    public WorldRef move(Direction dir) {
        return move(dir.toVect3i());
    }

	public IBlockData getBlockData() {
		return world.getBlockData(position);
	}

	public void setBlockData(IBlockData variant, int flags) {
		world.setBlockData(variant, position, flags);
	}

	public void setBlockData(IBlockData variant) {
		world.setBlockData(variant, position, 3);
	}

    public ITileEntity getTileEntity() {
        return world.getTileEntity(position);
    }

    public long getWorldTime() {
        return world.getWorldTime();
    }
}
