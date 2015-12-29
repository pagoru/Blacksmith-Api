package net.darkaqua.blacksmith.api.network.packet;

import net.darkaqua.blacksmith.api.storage.IDataCompound;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;

/**
 * Created by cout970 on 28/12/2015.
 */
public abstract class PacketFactory {

    protected static PacketFactory INSTANCE;

    public static ITileEntityUpdatePacket createTileEntityUpdatePacket(ITileEntity t, IDataCompound data) {
        return INSTANCE.newTileEntityUpdatePacket(t, data);
    }

    protected abstract ITileEntityUpdatePacket newTileEntityUpdatePacket(ITileEntity t, IDataCompound data);
}
