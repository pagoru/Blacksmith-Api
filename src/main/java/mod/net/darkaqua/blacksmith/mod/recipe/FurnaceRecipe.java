package net.darkaqua.blacksmith.mod.recipe;

import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.recipe.IFurnaceRecipe;

/**
 * Created by cout970 on 23/12/2015.
 */
public class FurnaceRecipe implements IFurnaceRecipe {

    private IItemStack input;
    private IItemStack output;
    private float experience;

    public FurnaceRecipe(IItemStack input, IItemStack output, float experience) {
        this.input = input;
        this.output = output;
        this.experience = experience;
    }

    @Override
    public IItemStack getInput() {
        return input;
    }

    @Override
    public IItemStack getOutput() {
        return output;
    }

    @Override
    public float getExperience() {
        return experience;
    }
}
