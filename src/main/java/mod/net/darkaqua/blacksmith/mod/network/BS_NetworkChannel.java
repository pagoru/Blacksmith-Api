package net.darkaqua.blacksmith.mod.network;

import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.network.INetworkChannel;
import net.darkaqua.blacksmith.api.network.INetworkMessage;
import net.darkaqua.blacksmith.api.network.INetworkMessageHandler;
import net.darkaqua.blacksmith.api.network.packet.IPacket;
import net.darkaqua.blacksmith.api.util.GameSide;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.mod.network.channel.SimpleChannel;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * Created by cout970 on 24/12/2015.
 */
public class BS_NetworkChannel implements INetworkChannel {

    private SimpleChannel network;

    public BS_NetworkChannel(SimpleChannel network) {
        this.network = network;
    }

    public SimpleChannel getNetwork() {
        return network;
    }

    @Override
    public <REQ extends INetworkMessage, REPLY extends INetworkMessage> void registerMessage(INetworkMessageHandler<? super REQ, ? extends REPLY> handler, Class<REQ> messageType, int discriminator, GameSide side) {
        network.registerMessage(handler, messageType, discriminator, MCInterface.toSide(side));
    }

    @Override
    public IPacket<?> getPacketFrom(INetworkMessage message) {
        return MCInterface.fromPacket(network.getPacketFrom(message));
    }

    @Override
    public void sendToAll(INetworkMessage message) {
        network.sendToAll(message);
    }

    @Override
    public void sendTo(INetworkMessage message, IPlayer player) {
        network.sendTo(message, (EntityPlayerMP) MCInterface.fromPlayer(player));
    }

    @Override
    public void sendToAllAround(INetworkMessage message, int dimension, double range, Vect3d pos) {
        network.sendToAllAround(message, new NetworkRegistry.TargetPoint(dimension, pos.getX(), pos.getY(), pos.getZ(), range));
    }

    @Override
    public void sendToDimension(INetworkMessage message, int dimension) {
        network.sendToDimension(message, dimension);
    }

    @Override
    public void sendToServer(INetworkMessage message) {
        network.sendToServer(message);
    }

    @Override
    public Object getInternalChannel() {
        return network;
    }
}
