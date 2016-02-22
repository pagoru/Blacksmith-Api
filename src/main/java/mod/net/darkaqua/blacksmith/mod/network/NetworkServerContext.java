package net.darkaqua.blacksmith.mod.network;

import net.darkaqua.blacksmith.api.network.INetworkContext;
import net.darkaqua.blacksmith.api.server.IServerHandler;
import net.darkaqua.blacksmith.api.world.IWorld;
import net.darkaqua.blacksmith.mod.registry.ServerHandler;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.DimensionManager;

/**
 * Created by cout970 on 17/01/2016.
 */
public class NetworkServerContext implements INetworkContext.IServerContext {

    @Override
    public IWorld getWorld(int dimension) {
        return MCInterface.fromWorld(DimensionManager.getWorld(dimension));
    }

    @Override
    public IServerHandler getServer() {
        return ServerHandler.INSTANCE;
    }

    @Override
    public void addScheduledTask(Runnable task) {
        MinecraftServer.getServer().addScheduledTask(task);
    }
}
