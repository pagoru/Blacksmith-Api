package net.darkaqua.blacksmith.mod.network.channel;

import com.google.common.base.Throwables;
import io.netty.channel.ChannelFutureListener;
import net.darkaqua.blacksmith.api.network.INetworkMessage;
import net.darkaqua.blacksmith.api.network.INetworkMessageHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.network.FMLEmbeddedChannel;
import net.minecraftforge.fml.common.network.FMLOutboundHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;

import java.util.EnumMap;

/**
 * Created by cout970 on 09/01/2016.
 */
public class SimpleChannel {

    private EnumMap<Side, FMLEmbeddedChannel> channels;
    private SimpleCodec packetCodec;

    public SimpleChannel(String channelName) {
        packetCodec = new SimpleCodec();
        channels = NetworkRegistry.INSTANCE.newChannel(channelName, packetCodec);
    }

    /**
     * Register a message and it's associated handler. The message will have the supplied discriminator byte. The message handler will
     * be registered on the supplied side (this is the side where you want the message to be processed and acted upon).
     *
     * @param messageHandler     the message handler type
     * @param requestMessageType the message type
     * @param discriminator      a discriminator byte
     * @param side               the side for the handler
     */
    public <REQ extends INetworkMessage, REPLY extends INetworkMessage> void registerMessage(Class<? extends INetworkMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, int discriminator, Side side) {
        registerMessage(instantiate(messageHandler), requestMessageType, discriminator, side);
    }

    static <REQ extends INetworkMessage, REPLY extends INetworkMessage> INetworkMessageHandler<? super REQ, ? extends REPLY> instantiate(Class<? extends INetworkMessageHandler<? super REQ, ? extends REPLY>> handler) {
        try {
            return handler.newInstance();
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }

    /**
     * Register a message and it's associated handler. The message will have the supplied discriminator byte. The message handler will
     * be registered on the supplied side (this is the side where you want the message to be processed and acted upon).
     *
     * @param messageHandler     the message handler instance
     * @param requestMessageType the message type
     * @param discriminator      a discriminator byte
     * @param side               the side for the handler
     */
    public <REQ extends INetworkMessage, REPLY extends INetworkMessage> void registerMessage(INetworkMessageHandler<? super REQ, ? extends REPLY> messageHandler, Class<REQ> requestMessageType, int discriminator, Side side) {
        packetCodec.addDiscriminator(discriminator, requestMessageType);
        FMLEmbeddedChannel channel = channels.get(side);
        String type = channel.findChannelHandlerNameForType(SimpleCodec.class);
        if (side == Side.SERVER) {
            addServerHandlerAfter(channel, type, messageHandler, requestMessageType);
        } else {
            addClientHandlerAfter(channel, type, messageHandler, requestMessageType);
        }
    }

    private <REQ extends INetworkMessage, REPLY extends INetworkMessage, NH extends INetHandler> void addServerHandlerAfter(FMLEmbeddedChannel channel, String type, INetworkMessageHandler<? super REQ, ? extends REPLY> messageHandler, Class<REQ> requestType) {
        ChannelHandler<REQ, REPLY> handler = getHandlerWrapper(messageHandler, Side.SERVER, requestType);
        channel.pipeline().addAfter(type, messageHandler.getClass().getName(), handler);
    }

    private <REQ extends INetworkMessage, REPLY extends INetworkMessage, NH extends INetHandler> void addClientHandlerAfter(FMLEmbeddedChannel channel, String type, INetworkMessageHandler<? super REQ, ? extends REPLY> messageHandler, Class<REQ> requestType) {
        ChannelHandler<REQ, REPLY> handler = getHandlerWrapper(messageHandler, Side.CLIENT, requestType);
        channel.pipeline().addAfter(type, messageHandler.getClass().getName(), handler);
    }

    private <REPLY extends INetworkMessage, REQ extends INetworkMessage> ChannelHandler<REQ, REPLY> getHandlerWrapper(INetworkMessageHandler<? super REQ, ? extends REPLY> messageHandler, Side side, Class<REQ> requestType) {
        return new ChannelHandler<>(messageHandler, side, requestType);
    }

    /**
     * Construct a minecraft packet from the supplied message. Can be used where minecraft packets are required, such as
     * {@link TileEntity#getDescriptionPacket}.
     *
     * @param message The message to translate into packet form
     * @return A minecraft {@link Packet} suitable for use in minecraft APIs
     */
    public Packet<?> getPacketFrom(INetworkMessage message) {
        return channels.get(Side.SERVER).generatePacketFrom(message);
    }

    /**
     * Send this message to everyone.
     * The {@link INetworkMessageHandler} for this message type should be on the CLIENT side.
     *
     * @param message The message to send
     */
    public void sendToAll(INetworkMessage message) {
        channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
        channels.get(Side.SERVER).writeAndFlush(message).addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
    }

    /**
     * Send this message to the specified player.
     * The {@link INetworkMessageHandler} for this message type should be on the CLIENT side.
     *
     * @param message The message to send
     * @param player  The player to send it to
     */
    public void sendTo(INetworkMessage message, EntityPlayerMP player) {
        channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
        channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
        channels.get(Side.SERVER).writeAndFlush(message).addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
    }

    /**
     * Send this message to everyone within a certain range of a point.
     * The {@link INetworkMessageHandler} for this message type should be on the CLIENT side.
     *
     * @param message The message to send
     * @param point   The {@link NetworkRegistry.TargetPoint} around which to send
     */
    public void sendToAllAround(INetworkMessage message, NetworkRegistry.TargetPoint point) {
        channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
        channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(point);
        channels.get(Side.SERVER).writeAndFlush(message).addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
    }

    /**
     * Send this message to everyone within the supplied dimension.
     * The {@link INetworkMessageHandler} for this message type should be on the CLIENT side.
     *
     * @param message     The message to send
     * @param dimensionId The dimension id to target
     */
    public void sendToDimension(INetworkMessage message, int dimensionId) {
        channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.DIMENSION);
        channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(dimensionId);
        channels.get(Side.SERVER).writeAndFlush(message).addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
    }

    /**
     * Send this message to the server.
     * The {@link INetworkMessageHandler} for this message type should be on the SERVER side.
     *
     * @param message The message to send
     */
    public void sendToServer(INetworkMessage message) {
        channels.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
        channels.get(Side.CLIENT).writeAndFlush(message).addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
    }
}
