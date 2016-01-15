package net.darkaqua.blacksmith.mod.registry;

import net.darkaqua.blacksmith.api.block.Blocks;
import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.item.IItem;
import net.darkaqua.blacksmith.api.item.Items;
import net.darkaqua.blacksmith.api.recipe.ICraftingRecipe;
import net.darkaqua.blacksmith.api.recipe.ICraftingRecipeDefinition;
import net.darkaqua.blacksmith.api.recipe.IFuelProvider;
import net.darkaqua.blacksmith.api.recipe.IFurnaceRecipe;
import net.darkaqua.blacksmith.api.registry.IRecipeRegistry;
import net.darkaqua.blacksmith.mod.recipe.BS_CraftingRecipe;
import net.darkaqua.blacksmith.mod.recipe.FurnaceRecipe;
import net.darkaqua.blacksmith.mod.recipe.RecipeWrapper;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by cout970 on 23/12/2015.
 */
public class RecipeRegistry implements IRecipeRegistry {

    public static final RecipeRegistry INSTANCE = new RecipeRegistry();

    private RecipeRegistry() {
    }

    @Override
    public List<ICraftingRecipe> getCraftingRecipes() {
        return CraftingManager.getInstance().getRecipeList().stream().map(RecipeWrapper::new).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<IFurnaceRecipe> getFurnaceRecipes() {
        List<IFurnaceRecipe> list = new LinkedList<>();
        for (Map.Entry<ItemStack, ItemStack> e : FurnaceRecipes.instance().getSmeltingList().entrySet()) {
            float exp = FurnaceRecipes.instance().getSmeltingExperience(e.getKey());
            list.add(new FurnaceRecipe(MCInterface.fromItemStack(e.getKey()), MCInterface.fromItemStack(e.getValue()), exp));
        }
        return list;
    }

    @Override
    public void addCraftingRecipe(ICraftingRecipeDefinition recipe) {
        GameRegistry.addRecipe(new BS_CraftingRecipe(recipe));
    }

    @Override
    public void addShapedCraftingRecipe(IItemStack output, Object... recipe) {
        GameRegistry.addShapedRecipe(MCInterface.toItemStack(output), replaceRecipe(recipe));
    }

    @Override
    public void addShapelessCraftingRecipe(IItemStack output, Object... recipe) {
        GameRegistry.addShapelessRecipe(MCInterface.toItemStack(output), replaceRecipe(recipe));
    }

    @Override
    public void addOreDictionaryShapedRecipe(IItemStack output, Object... recipe) {
        GameRegistry.addRecipe(new ShapedOreRecipe(MCInterface.toItemStack(output), replaceRecipe(recipe)));
    }

    @Override
    public void addOreDictionaryShapelessRecipe(IItemStack output, Object... recipe) {
        GameRegistry.addRecipe(new ShapelessOreRecipe(MCInterface.toItemStack(output), replaceRecipe(recipe)));
    }

    @Override
    public void addFurnaceRecipe(IItemStack output, IItemStack input, float experience) {
        GameRegistry.addSmelting(MCInterface.toItemStack(output), MCInterface.toItemStack(input), experience);
    }

    @Override
    public int getFurnaceFuelValue(IItemStack stack) {
        return GameRegistry.getFuelValue(MCInterface.toItemStack(stack));
    }

    @Override
    public void registerFuelProvider(final IFuelProvider provider) {
        GameRegistry.registerFuelHandler(fuel -> provider.getBurnTime(MCInterface.fromItemStack(fuel)));
    }

    private Object[] replaceRecipe(Object[] recipe) {
        for (int i = 0; i < recipe.length; i++) {
            if (recipe[i] instanceof IItemStack) {
                recipe[i] = MCInterface.toItemStack((IItemStack) recipe[i]);
            }
            if (recipe[i] instanceof IItem) {
                recipe[i] = MCInterface.toItem((IItem) recipe[i]);
            }
            if (recipe[i] instanceof IBlock) {
                recipe[i] = MCInterface.toBlock((IBlock) recipe[i]);
            }
            if (recipe[i] instanceof Items) {
                recipe[i] = MCInterface.toItem(((Items) recipe[i]).getItem());
            }
            if (recipe[i] instanceof Blocks) {
                recipe[i] = MCInterface.toBlock(((Blocks) recipe[i]).getBlock());
            }
        }
        return recipe;
    }
}
