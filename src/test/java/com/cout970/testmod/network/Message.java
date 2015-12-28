package com.cout970.testmod.network;

import io.netty.buffer.ByteBuf;
import net.darkaqua.blacksmith.api.network.INetworkMessage;
import net.darkaqua.blacksmith.mod.util.Log;

/**
 * Created by cout970 on 24/12/2015.
 */
public class Message implements INetworkMessage {

    @Override
    public void fromBytes(ByteBuf buf) {
        Log.debug("fromBytes");
    }

    @Override
    public void toBytes(ByteBuf buf) {
        Log.debug("toBytes");
    }
}
