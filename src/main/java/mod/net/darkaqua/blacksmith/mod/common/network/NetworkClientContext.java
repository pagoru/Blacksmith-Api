package net.darkaqua.blacksmith.mod.common.network;

import net.darkaqua.blacksmith.api.common.entity.IPlayer;
import net.darkaqua.blacksmith.api.common.network.INetworkContext;
import net.darkaqua.blacksmith.api.common.world.IWorld;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
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
