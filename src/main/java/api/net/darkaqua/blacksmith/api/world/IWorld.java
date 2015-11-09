package net.darkaqua.blacksmith.api.world;

import net.darkaqua.blacksmith.api.block.IIBlockState;
import net.darkaqua.blacksmith.api.util.Vector3i;

public interface IWorld extends IWorldAccess {

	IIBlockState getBlockState(Vector3i pos);

}
