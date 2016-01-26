package net.darkaqua.blacksmith.api.world;

import net.darkaqua.blacksmith.api.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.util.Vect3i;

public interface IWorldAccess {

    IBlockData getBlockData(Vect3i position);

    ITileEntity getTileEntity(Vect3i position);
}
