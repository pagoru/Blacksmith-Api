package net.darkaqua.blacksmith.mod.registry;

import net.darkaqua.blacksmith.api.server.IServerHandler;
import net.darkaqua.blacksmith.api.world.IWorld;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraftforge.common.DimensionManager;

/**
 * Created by cout970 on 18/01/2016.
 */
public class ServerHandler implements IServerHandler {

    public static final ServerHandler INSTANCE = new ServerHandler();

    private ServerHandler(){}

    @Override
    public IWorld getWorld(int dim) {
        return MCInterface.fromWorld(DimensionManager.getWorld(dim));
    }

}
