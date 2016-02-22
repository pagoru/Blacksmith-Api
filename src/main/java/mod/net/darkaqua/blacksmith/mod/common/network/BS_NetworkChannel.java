package net.darkaqua.blacksmith.mod.common.network;

import net.darkaqua.blacksmith.api.common.entity.IPlayer;
import net.darkaqua.blacksmith.api.common.network.INetworkChannel;
import net.darkaqua.blacksmith.api.common.network.INetworkMessage;
import net.darkaqua.blacksmith.api.common.network.INetworkMessageHandler;
import net.darkaqua.blacksmith.api.common.util.GameSide;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;
import net.darkaqua.blacksmith.mod.common.network.channel.SimpleChannel;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
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
