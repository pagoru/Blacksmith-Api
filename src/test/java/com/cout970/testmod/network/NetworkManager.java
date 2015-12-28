package com.cout970.testmod.network;

import net.darkaqua.blacksmith.api.network.INetworkChannel;
import net.darkaqua.blacksmith.api.network.NetworkChannelFactory;
import net.darkaqua.blacksmith.api.util.GameSide;

/**
 * Created by cout970 on 24/12/2015.
 */
public class NetworkManager {

    public static INetworkChannel channel;

    public static void init(){
        channel = NetworkChannelFactory.createNetworkChannel("test_channel");
        channel.registerMessage(new MessageHandler(), Message.class, 0 , GameSide.SERVER);
    }
}
