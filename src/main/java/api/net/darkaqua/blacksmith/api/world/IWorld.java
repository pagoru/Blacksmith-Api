package net.darkaqua.blacksmith.api.world;

import net.darkaqua.blacksmith.api.block.blockstate.IBlockVariant;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.util.Vector3i;

public interface IWorld extends IWorldAccess {

	IBlockVariant getBlockState(Vector3i position);

	ITileEntity getTileEntity(Vector3i position);
}
