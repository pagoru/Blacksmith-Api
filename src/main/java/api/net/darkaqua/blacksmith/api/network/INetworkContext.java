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

        void addScheduledTask(Runnable task);

        IPlayer getPlayer();

        IWorld getWorld();
    }

    interface IServerContext {

        void addScheduledTask(Runnable task);

        IWorld getWorld(int dimension);

        IServerHandler getServer();
    }
}
