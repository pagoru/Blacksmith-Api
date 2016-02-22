package net.darkaqua.blacksmith.mod.common.network.channel;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.darkaqua.blacksmith.api.common.network.INetworkMessage;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.FMLIndexedMessageToMessageCodec;

/**
 * Created by cout970 on 09/01/2016.
 */
public class SimpleCodec extends FMLIndexedMessageToMessageCodec<INetworkMessage> {

    @Override
    public void encodeInto(ChannelHandlerContext ctx, INetworkMessage msg, ByteBuf target) throws Exception {
        msg.toBytes(target, new BS_ExtendedByteBuf(new PacketBuffer(target)));
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf source, INetworkMessage msg) {
        msg.fromBytes(source, new BS_ExtendedByteBuf(new PacketBuffer(source)));
    }
}
