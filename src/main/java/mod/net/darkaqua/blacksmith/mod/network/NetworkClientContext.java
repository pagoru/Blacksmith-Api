package net.darkaqua.blacksmith.mod.network;

import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.network.INetworkContext;
import net.darkaqua.blacksmith.api.world.IWorld;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.client.Minecraft;

/**
 * Created by cout970 on 17/01/2016.
 */
public class NetworkClientContext implements INetworkContext.IClientContext {

    @Override
    public void addScheduledTask(Runnable task) {
        Minecraft.getMinecraft().addScheduledTask(task);
    }

    @Override
    public IPlayer getPlayer() {
        return MCInterface.toPlayer(Minecraft.getMinecraft().thePlayer);
    }

    @Override
    public IWorld getWorld() {
        return MCInterface.fromWorld(Minecraft.getMinecraft().theWorld);
    }
}
