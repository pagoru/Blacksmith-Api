package net.darkaqua.blacksmith.api.network;

/**
 * Created by cout970 on 24/12/2015.
 */
public interface INetworkMessageHandler<REQ extends INetworkMessage, REPLY extends INetworkMessage> {

    /**
     * Called when a message is received of the appropriate type. You can optionally return a reply message, or null if no reply
     * is needed.
     *
     * @param message The message
     * @return an optional return message
     */
    REPLY onMessage(REQ message, INetworkContext context);
}
