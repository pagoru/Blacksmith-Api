package net.darkaqua.blacksmith.api.registry;

/**
 * Created by cout970 on 08/11/2015.
 */
public interface IGame {

    IBlockRegistry getBlockRegistry();

    ITileEntityRegistry getTileEntityRegistry();

    boolean isClient();

    boolean isServer();
}
