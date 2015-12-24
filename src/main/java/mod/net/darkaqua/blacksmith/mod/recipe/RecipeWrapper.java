package net.darkaqua.blacksmith.mod.recipe;

import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.recipe.ICraftingGrid;
import net.darkaqua.blacksmith.api.recipe.ICraftingRecipe;
import net.darkaqua.blacksmith.api.world.IWorld;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

/**
 * Created by cout970 on 23/12/2015.
 */
public class RecipeWrapper implements ICraftingRecipe {

    private IRecipe recipe;

    public RecipeWrapper(IRecipe recipe) {
        this.recipe = recipe;
    }

    public IRecipe getRecipe() {
        return recipe;
    }

    @Override
    public boolean matchesRecipe(IWorld w, ICraftingGrid grid) {
        return recipe.matches(MCInterface.toInventoryCrafting(grid), MCInterface.toWorld(w));
    }

    @Override
    public IItemStack getCraftingResult(ICraftingGrid grid) {
        return MCInterface.fromItemStack(recipe.getCraftingResult(MCInterface.toInventoryCrafting(grid)));
    }

    @Override
    public IItemStack getRecipeOutput() {
        return MCInterface.fromItemStack(recipe.getRecipeOutput());
    }

    @Override
    public int getRecipeSize() {
        return recipe.getRecipeSize();
    }

    @Override
    public IItemStack[] getRemainingItems(ICraftingGrid grid) {
        ItemStack[] remaining = recipe.getRemainingItems(MCInterface.toInventoryCrafting(grid));
        IItemStack[] result = new IItemStack[remaining.length];
        for (int i = 0; i < remaining.length; i++) {
            result[i] = MCInterface.fromItemStack(remaining[i]);
        }
        return result;
    }

    @Override
    public Object getInternalRecipe() {
        return recipe;
    }
}
