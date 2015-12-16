package net.darkaqua.blacksmith.api.world;

import net.darkaqua.blacksmith.api.block.IBlockVariant;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.util.Vect3i;

public interface IWorld extends IWorldAccess {

	IBlockVariant getBlockState(Vect3i position);

	ITileEntity getTileEntity(Vect3i position);
}
