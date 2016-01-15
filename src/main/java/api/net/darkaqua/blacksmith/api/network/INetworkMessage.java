package net.darkaqua.blacksmith.api.network;

import io.netty.buffer.ByteBuf;

/**
 * Created by cout970 on 24/12/2015.
 */
public interface INetworkMessage {

    /**
     * Convert from the supplied buffer into your specific message type
     *
     * @param buf
     */
    void fromBytes(ByteBuf buf, ExtendedByteBuf helper);

    /**
     * Deconstruct your message into the supplied byte buffer
     *
     * @param buf
     */
    void toBytes(ByteBuf buf, ExtendedByteBuf helper);
}
