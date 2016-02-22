package net.darkaqua.blacksmith.api.common.world;

import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.common.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3i;

public interface IWorldAccess {

    IBlockData getBlockData(Vect3i position);

    ITileEntity getTileEntity(Vect3i position);
}
