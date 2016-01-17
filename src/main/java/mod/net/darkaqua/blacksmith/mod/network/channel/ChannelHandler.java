package net.darkaqua.blacksmith.mod.network.channel;

import com.google.common.base.Preconditions;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.darkaqua.blacksmith.api.network.INetworkMessage;
import net.darkaqua.blacksmith.api.network.INetworkMessageHandler;
import net.darkaqua.blacksmith.mod.network.NetworkContext;
import net.minecraft.network.INetHandler;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.network.FMLOutboundHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Level;

/**
 * Created by cout970 on 09/01/2016.
 */
public class ChannelHandler<REQ extends INetworkMessage, REPLY extends INetworkMessage> extends SimpleChannelInboundHandler<REQ> {

    private final INetworkMessageHandler<? super REQ, ? extends REPLY> messageHandler;
    private final Side side;

    public ChannelHandler(Class<? extends INetworkMessageHandler<? super REQ, ? extends REPLY>> handler, Side side, Class<REQ> requestType) {
        this(SimpleChannel.instantiate(handler), side, requestType);
    }

    public ChannelHandler(INetworkMessageHandler<? super REQ, ? extends REPLY> handler, Side side, Class<REQ> requestType) {
        super(requestType);
        messageHandler = Preconditions.checkNotNull(handler, "IMessageHandler must not be null");
        this.side = side;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, REQ msg) throws Exception {
        INetHandler iNetHandler = ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
        REPLY result = messageHandler.onMessage(msg, new NetworkContext());
        if (result != null) {
            ctx.channel().attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.REPLY);
            ctx.writeAndFlush(result).addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        FMLLog.log(Level.ERROR, cause, "SimpleChannel exception");
        super.exceptionCaught(ctx, cause);
    }
}