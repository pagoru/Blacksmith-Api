package net.darkaqua.blacksmith.mod.common.world;

import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.common.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3i;
import net.darkaqua.blacksmith.api.common.world.IWorldAccess;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
import net.minecraft.world.IBlockAccess;

/**
 * Created by cout970 on 08/11/2015.
 */
public class IBlockAccessWrapper implements IWorldAccess {

    private IBlockAccess access;

    public IBlockAccessWrapper(IBlockAccess access) {
        this.access = access;
    }

    public IBlockAccess getWorldAccess() {
        return access;
    }

    @Override
    public IBlockData getBlockData(Vect3i position) {
        return MCInterface.fromBlockState(access.getBlockState(MCInterface.toBlockPos(position)));
    }

    @Override
    public ITileEntity getTileEntity(Vect3i position) {
        return MCInterface.fromTileEntity(access.getTileEntity(MCInterface.toBlockPos(position)));
    }
}
