package net.darkaqua.blacksmith.mod.network.packet;

import net.darkaqua.blacksmith.api.network.packet.ITileEntityUpdatePacket;
import net.darkaqua.blacksmith.api.network.packet.PacketFactory;
import net.darkaqua.blacksmith.api.storage.IDataCompound;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

/**
 * Created by cout970 on 28/12/2015.
 */
public class BS_PacketFactory extends PacketFactory {

    public static void init() {
        INSTANCE = new BS_PacketFactory();
    }

    private BS_PacketFactory() {
    }

    @Override
    protected ITileEntityUpdatePacket newTileEntityUpdatePacket(ITileEntity t, IDataCompound data) {
        return new DescriptionPacketWrapper(new S35PacketUpdateTileEntity(MCInterface.toTileEntity(t).getPos(), 0, MCInterface.toNBTCompound(data)));
    }
}
