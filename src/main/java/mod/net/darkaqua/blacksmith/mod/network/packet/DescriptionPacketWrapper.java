package net.darkaqua.blacksmith.mod.network.packet;

import net.darkaqua.blacksmith.api.network.packet.IDescriptionPacket;
import net.darkaqua.blacksmith.api.storage.IDataCompound;
import net.darkaqua.blacksmith.api.util.Vector3i;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

/**
 * Created by cout970 on 15/11/2015.
 */
public class DescriptionPacketWrapper implements IDescriptionPacket {

    private S35PacketUpdateTileEntity packet;

    public DescriptionPacketWrapper(S35PacketUpdateTileEntity packet){
        this.packet = packet;
    }

    public S35PacketUpdateTileEntity getPacket(){
        return packet;
    }

    @Override
    public Vector3i getPosition() {
        return MCInterface.fromBlockPos(packet.func_179823_a());
    }
    @Override
    public IDataCompound getDataCompound() {
        return MCInterface.fromNBTCompound(packet.getNbtCompound());
    }
}
