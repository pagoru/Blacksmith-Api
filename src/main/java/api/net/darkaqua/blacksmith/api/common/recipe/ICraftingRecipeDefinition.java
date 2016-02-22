package net.darkaqua.blacksmith.api.common.recipe;

import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.common.util.annotations.Implementable;
import net.darkaqua.blacksmith.api.common.world.IWorld;

/**
 * Created by cout970 on 23/12/2015.
 */
@Implementable
public interface ICraftingRecipeDefinition {

    /**
     * Returns true if the content of the crafting grid matches the recipe
     */
    boolean matchesRecipe(IWorld w, ICraftingGrid grid);

    /**
     * Return the result of the crafting recipe
     */
    IItemStack getCraftingResult(ICraftingGrid grid);

    /**
     * Return the result of the crafting recipe
     */
    IItemStack getRecipeOutput();

    /**
     * Returns the minimum size of the crafting grid needed to use this recipe
     * Ej: 3 to 3x3, or 2 to 2x2
     */
    int getRecipeSize();

    IItemStack[] getRemainingItems(ICraftingGrid grid);
}
