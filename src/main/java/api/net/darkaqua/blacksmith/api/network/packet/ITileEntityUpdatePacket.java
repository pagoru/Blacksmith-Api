package net.darkaqua.blacksmith.api.network.packet;

import net.darkaqua.blacksmith.api.storage.IDataCompound;
import net.darkaqua.blacksmith.api.util.Vect3i;

/**
 * Created by cout970 on 15/11/2015.
 */
public interface ITileEntityUpdatePacket extends IPacket {

    Vect3i getPosition();

    IDataCompound getDataCompound();
}
