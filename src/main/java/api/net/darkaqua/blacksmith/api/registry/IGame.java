package net.darkaqua.blacksmith.api.registry;

import net.darkaqua.blacksmith.api.server.IServerHandler;

/**
 * Created by cout970 on 08/11/2015.
 */
public interface IGame {

    //Common

    IBlockRegistry getBlockRegistry();

    IItemRegistry getItemRegistry();

    ITileEntityRegistry getTileEntityRegistry();

    IWorldGenerationRegistry getWorldGenerationRegistry();

    IResourceManager getResourceManager();

    IOreDictionary getOreDictionary();

    IFluidRegistry getFluidRegistry();

    IRecipeRegistry getRecipeRegistry();

    IGuiRegistry getGuiRegistry();

    IInterModRegistry getInterModRegistry();

    //Server

    IServerHandler getServer();

    //Client

    IRenderRegistry getRenderRegistry();

    IRenderManager getRenderManager();

    IParticleManager getParticleManager();

    ISoundHandler getSoundHandler();

    // Misc

    boolean isClient();

    boolean isServer();

    boolean isDedicatedServer();

    boolean isDeobfuscatedEnvironment();
}
