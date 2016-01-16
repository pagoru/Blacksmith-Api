package net.darkaqua.blacksmith.api.registry;

/**
 * Created by cout970 on 08/11/2015.
 */
//TODO change this to a static class
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

    boolean isClient();

    boolean isServer();

    boolean isDeobfuscatedEnvironment();
}
