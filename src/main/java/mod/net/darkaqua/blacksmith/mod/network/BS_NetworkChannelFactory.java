package net.darkaqua.blacksmith.mod.network;

import net.darkaqua.blacksmith.api.network.INetworkChannel;
import net.darkaqua.blacksmith.api.network.NetworkChannelFactory;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * Created by cout970 on 24/12/2015.
 */
public class BS_NetworkChannelFactory extends NetworkChannelFactory{

    private BS_NetworkChannelFactory(){}

    public static void init(){
        INSTANCE = new BS_NetworkChannelFactory();
    }

    @Override
    protected INetworkChannel newNetworkChannel(String channelName) {
        return new BS_NetworkChannel(NetworkRegistry.INSTANCE.newSimpleChannel(channelName));
    }
}
