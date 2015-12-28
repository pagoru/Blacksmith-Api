package net.darkaqua.blacksmith.api.network;

/**
 * Created by cout970 on 24/12/2015.
 */
public abstract class NetworkChannelFactory {

    protected static NetworkChannelFactory INSTANCE;

    public static INetworkChannel createNetworkChannel(String channelName){
        return INSTANCE.newNetworkChannel(channelName);
    }

    protected abstract INetworkChannel newNetworkChannel(String channelName);
}
