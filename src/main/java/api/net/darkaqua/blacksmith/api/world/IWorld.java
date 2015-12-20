package net.darkaqua.blacksmith.api.world;

import net.darkaqua.blacksmith.api.block.IBlockVariant;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.util.Vect3i;

public interface IWorld extends IWorldAccess {

	IBlockVariant getBlockVariant(Vect3i position);

	//flags argument will be changed
	boolean setBlockVariant(IBlockVariant variant, Vect3i posiction, int flags);

	ITileEntity getTileEntity(Vect3i position);

	void removeTileEntity(Vect3i position);

	boolean isBlockLoaded(Vect3i position);

	boolean isAreaLoaded(Cube area);

	int getWorldDimension();

	IIChunkProvider getChunkProvider();
}
