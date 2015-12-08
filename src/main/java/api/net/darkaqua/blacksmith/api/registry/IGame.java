package net.darkaqua.blacksmith.api.registry;

/**
 * Created by cout970 on 08/11/2015.
 */
public interface IGame {

    IBlockRegistry getBlockRegistry();

    IRenderRegistry getRenderRegistry();

    ITileEntityRegistry getTileEntityRegistry();

    boolean isClient();

    boolean isServer();

    boolean isDeobfuscatedEnvironment();
}
