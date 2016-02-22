package net.darkaqua.blacksmith.mod.common.network;

import net.darkaqua.blacksmith.api.common.network.INetworkContext;
import net.darkaqua.blacksmith.api.server.IServerHandler;
import net.darkaqua.blacksmith.api.common.world.IWorld;
import net.darkaqua.blacksmith.mod.common.registry.ServerHandler;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
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
