package net.darkaqua.blacksmith.api.world;

import net.darkaqua.blacksmith.api.block.blockstate.IIBlockState;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.util.Vector3i;

public interface IWorld extends IWorldAccess {

	IIBlockState getBlockState(Vector3i position);

	ITileEntity getTileEntity(Vector3i position);
}
