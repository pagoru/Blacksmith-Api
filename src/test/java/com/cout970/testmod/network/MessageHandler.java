package com.cout970.testmod.network;

import net.darkaqua.blacksmith.api.network.INetworkContext;
import net.darkaqua.blacksmith.api.network.INetworkMessageHandler;

/**
 * Created by cout970 on 24/12/2015.
 */
public class MessageHandler implements INetworkMessageHandler<Message, Message> {


    @Override
    public Message onMessage(Message message, INetworkContext context) {
        return null;
    }
}
