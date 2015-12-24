package net.darkaqua.blacksmith.api.recipe;

import net.darkaqua.blacksmith.api.inventory.container.IContainer;

/**
 * Created by cout970 on 23/12/2015.
 */
public abstract class CraftingGridFactory {

    protected static CraftingGridFactory INSTANCE;

    public static ICraftingGrid createCraftingGrid(IContainer container, int height, int width){
        return INSTANCE.newCraftingGrid(container, height, width);
        //TODO
    }

    protected abstract ICraftingGrid newCraftingGrid(IContainer container, int height, int width);
}
