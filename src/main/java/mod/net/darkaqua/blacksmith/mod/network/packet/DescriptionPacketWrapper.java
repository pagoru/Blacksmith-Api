package net.darkaqua.blacksmith.mod.network.packet;

import net.darkaqua.blacksmith.api.network.packet.ITileEntityUpdatePacket;
import net.darkaqua.blacksmith.api.storage.IDataCompound;
import net.darkaqua.blacksmith.api.util.Vect3i;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

/**
 * Created by cout970 on 15/11/2015.
 */
public class DescriptionPacketWrapper implements ITileEntityUpdatePacket {

    private S35PacketUpdateTileEntity packet;

    public DescriptionPacketWrapper(S35PacketUpdateTileEntity packet) {
        this.packet = packet;
    }

    public S35PacketUpdateTileEntity getPacket() {
        return packet;
    }

    @Override
    public Vect3i getPosition() {
        return MCInterface.fromBlockPos(packet.getPos());
    }

    @Override
    public IDataCompound getDataCompound() {
        return MCInterface.fromNBTCompound(packet.getNbtCompound());
    }
}
