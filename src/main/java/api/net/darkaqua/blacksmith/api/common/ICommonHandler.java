package net.darkaqua.blacksmith.api.common;

import net.darkaqua.blacksmith.api.common.registry.*;

/**
 * Created by cout970 on 22/02/2016.
 */
public interface ICommonHandler {

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

    IModManager getModManager();
}
