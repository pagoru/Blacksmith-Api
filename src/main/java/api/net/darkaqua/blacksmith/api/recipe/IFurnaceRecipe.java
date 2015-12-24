package net.darkaqua.blacksmith.api.recipe;

import net.darkaqua.blacksmith.api.inventory.IItemStack;

/**
 * Created by cout970 on 23/12/2015.
 */
public interface IFurnaceRecipe {

    IItemStack getInput();
    IItemStack getOutput();
    float getExperience();

}
