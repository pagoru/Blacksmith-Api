package net.darkaqua.blacksmith.api.network;

import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.server.IServerHandler;
import net.darkaqua.blacksmith.api.util.GameSide;
import net.darkaqua.blacksmith.api.world.IWorld;

/**
 * Created by cout970 on 09/01/2016.
 */
public interface INetworkContext {

    GameSide getSide();

    IClientContext getClientContext();

    IServerContext getServerContext();

    interface IClientContext {

        IWorld getWorld();
    }

    interface IServerContext {

        IPlayer getPlayer();

        IServerHandler getServer();
    }
}
