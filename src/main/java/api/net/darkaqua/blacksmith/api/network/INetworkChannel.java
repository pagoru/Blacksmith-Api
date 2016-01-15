package net.darkaqua.blacksmith.api.network;

import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.network.packet.IPacket;
import net.darkaqua.blacksmith.api.util.GameSide;
import net.darkaqua.blacksmith.api.util.Vect3d;

/**
 * Created by cout970 on 24/12/2015.
 */
public interface INetworkChannel {

    <REQ extends INetworkMessage, REPLY extends INetworkMessage> void registerMessage(INetworkMessageHandler<? super REQ, ? extends REPLY> handler, Class<REQ> messageType, int discriminator, GameSide gameSide);

    IPacket getPacketFrom(INetworkMessage message);

    void sendToAll(INetworkMessage message);

    void sendTo(INetworkMessage message, IPlayer player);

    void sendToAllAround(INetworkMessage message, int dimension, double range, Vect3d pos);

    void sendToDimension(INetworkMessage message, int dimension);

    void sendToServer(INetworkMessage message);

    Object getInternalChannel();
}
