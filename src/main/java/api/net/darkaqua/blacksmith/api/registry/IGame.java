package net.darkaqua.blacksmith.api.registry;

import net.darkaqua.blacksmith.api.server.IServerHandler;

/**
 * Created by cout970 on 08/11/2015.
 */
public interface IGame {

    IBlockRegistry getBlockRegistry();

    IItemRegistry getItemRegistry();

    IRenderRegistry getRenderRegistry();

    ITileEntityRegistry getTileEntityRegistry();

    IWorldGenerationRegistry getWorldGenerationRegistry();

    IResourceManager getResourceManager();

    IRenderManager getRenderManager();

    IOreDictionary getOreDictionary();

    IFluidRegistry getFluidRegistry();

    IRecipeRegistry getRecipeRegistry();

    IGuiRegistry getGuiRegistry();

    IInterModRegistry getInterModRegistry();

    IParticleManager getParticleManager();

    IServerHandler getServer();

    boolean isClient();

    boolean isServer();

    boolean isDedicatedServer();

    boolean isDeobfuscatedEnvironment();
}
