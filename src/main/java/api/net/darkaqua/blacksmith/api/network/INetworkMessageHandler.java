package net.darkaqua.blacksmith.api.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;

/**
 * Created by cout970 on 24/12/2015.
 */
public interface INetworkMessageHandler<REQ extends INetworkMessage, REPLY extends INetworkMessage> extends IMessageHandler<REQ, REPLY> {}
