package net.darkaqua.blacksmith.api.common.registry;

import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.common.recipe.ICraftingRecipe;
import net.darkaqua.blacksmith.api.common.recipe.ICraftingRecipeDefinition;
import net.darkaqua.blacksmith.api.common.recipe.IFuelProvider;
import net.darkaqua.blacksmith.api.common.recipe.IFurnaceRecipe;

import java.util.List;

/**
 * Created by cout970 on 23/12/2015.
 */
public interface IRecipeRegistry {

    List<ICraftingRecipe> getCraftingRecipes();

    List<IFurnaceRecipe> getFurnaceRecipes();

    void addCraftingRecipe(ICraftingRecipeDefinition recipe);

    void addShapedCraftingRecipe(IItemStack output, Object... recipe);

    void addShapelessCraftingRecipe(IItemStack output, Object... recipe);

    void addOreDictionaryShapedRecipe(IItemStack output, Object... recipe);

    void addOreDictionaryShapelessRecipe(IItemStack output, Object... recipe);

    void addFurnaceRecipe(IItemStack input, IItemStack output, float experience);

    int getFurnaceFuelValue(IItemStack stack);

    void registerFuelProvider(IFuelProvider provider);
}
