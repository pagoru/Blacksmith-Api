package net.darkaqua.blacksmith.mod.common;

import net.darkaqua.blacksmith.api.common.ICommonHandler;
import net.darkaqua.blacksmith.api.common.registry.*;
import net.darkaqua.blacksmith.mod.common.registry.*;

/**
 * Created by cout970 on 22/02/2016.
 */
public class CommonHandler implements ICommonHandler {

    public static final CommonHandler INSTANCE = new CommonHandler();

    private CommonHandler(){}

    @Override
    public IBlockRegistry getBlockRegistry() {
        return BlockRegistry.INSTANCE;
    }

    @Override
    public IItemRegistry getItemRegistry() {
        return ItemRegistry.INSTANCE;
    }

    @Override
    public ITileEntityRegistry getTileEntityRegistry() {
        return TileEntityRegistry.INSTANCE;
    }

    @Override
    public IWorldGenerationRegistry getWorldGenerationRegistry() {
        return WorldGenerationRegistry.INSTANCE;
    }

    @Override
    public IResourceManager getResourceManager() {
        return ResourceManager.INSTANCE;
    }

    @Override
    public IOreDictionary getOreDictionary() {
        return OreDictionaryManager.INSTANCE;
    }

    @Override
    public IFluidRegistry getFluidRegistry() {
        return FluidRegistryManager.INSTANCE;
    }

    @Override
    public IRecipeRegistry getRecipeRegistry() {
        return RecipeRegistry.INSTANCE;
    }

    @Override
    public IGuiRegistry getGuiRegistry() {
        return GuiRegistry.INSTANCE;
    }

    @Override
    public IInterModRegistry getInterModRegistry() {
        return InterModRegistry.INSTANCE;
    }

    @Override
    public IModManager getModManager() {
        return ModManager.INSTANCE;
    }
}
