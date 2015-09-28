package net.darkaqua.blacksmith.api.world;

import net.darkaqua.blacksmith.api.block.IIBlockState;
import net.darkaqua.blacksmith.api.util.BlockLoc;

public interface IWorld extends IWorldAccess {

	IIBlockState getBlockState(BlockLoc pos);

}
