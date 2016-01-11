package net.darkaqua.blacksmith.api.server;

import net.darkaqua.blacksmith.api.world.IWorld;

/**
 * Created by cout970 on 09/01/2016.
 */
public interface IServerHandler {

    IWorld getWorld(int dim);
}
