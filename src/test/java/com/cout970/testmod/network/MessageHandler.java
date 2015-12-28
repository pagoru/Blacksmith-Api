package com.cout970.testmod.network;

import net.darkaqua.blacksmith.api.network.INetworkMessageHandler;
import net.darkaqua.blacksmith.mod.util.Log;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by cout970 on 24/12/2015.
 */
public class MessageHandler implements INetworkMessageHandler<Message, Message> {

    @Override
    public Message onMessage(Message message, MessageContext ctx) {
        Log.debug("onMessage");
        return null;
    }
}
