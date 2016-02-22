package net.darkaqua.blacksmith.mod.network;

import net.darkaqua.blacksmith.api.network.INetworkContext;
import net.darkaqua.blacksmith.api.registry.StaticAccess;
import net.darkaqua.blacksmith.api.util.GameSide;

/**
 * Created by cout970 on 17/01/2016.
 */
public class NetworkContext implements INetworkContext {

    @Override
    public GameSide getSide() {
        return StaticAccess.GAME.isClient() ? GameSide.CLIENT : GameSide.SERVER;
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
