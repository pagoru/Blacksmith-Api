package net.darkaqua.blacksmith.mod.recipe;

import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.recipe.ICraftingRecipeDefinition;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

/**
 * Created by cout970 on 23/12/2015.
 */
public class BS_CraftingRecipe implements IRecipe {

    private ICraftingRecipeDefinition recipe;

    public BS_CraftingRecipe(ICraftingRecipeDefinition recipe) {
        this.recipe = recipe;
    }

    public ICraftingRecipeDefinition getRecipe() {
        return recipe;
    }

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        return recipe.matchesRecipe(MCInterface.fromWorld(worldIn), MCInterface.fromInventoryCrafting(inv));
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        return MCInterface.toItemStack(recipe.getCraftingResult(MCInterface.fromInventoryCrafting(inv)));
    }

    @Override
    public int getRecipeSize() {
        return recipe.getRecipeSize();
    }

    @Override
    public ItemStack getRecipeOutput() {
        return MCInterface.toItemStack(recipe.getRecipeOutput());
    }

    @Override
    public ItemStack[] getRemainingItems(InventoryCrafting inv) {
        IItemStack[] stack = recipe.getRemainingItems(MCInterface.fromInventoryCrafting(inv));
        ItemStack[] result = new ItemStack[stack.length];
        for (int i = 0; i < stack.length; i++) {
            result[i] = MCInterface.toItemStack(stack[i]);
        }
        return result;
    }
}
