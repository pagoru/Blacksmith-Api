package net.darkaqua.blacksmith.mod.common.network;

import net.darkaqua.blacksmith.api.Game;
import net.darkaqua.blacksmith.api.common.network.INetworkContext;
import net.darkaqua.blacksmith.api.common.util.GameSide;

/**
 * Created by cout970 on 17/01/2016.
 */
public class NetworkContext implements INetworkContext {

    @Override
    public GameSide getSide() {
        return Game.isClient() ? GameSide.CLIENT : GameSide.SERVER;
    }

    @Override
    public IClientContext getClientContext() {
        return new NetworkClientContext();
    }

    @Override
    public IServerContext getServerContext() {
        return new NetworkServerContext();
    }

}
