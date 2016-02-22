package net.darkaqua.blacksmith.api.common.recipe;

import net.darkaqua.blacksmith.api.common.inventory.IItemStack;

/**
 * Created by cout970 on 23/12/2015.
 */
public interface IFurnaceRecipe {

    IItemStack getInput();

    IItemStack getOutput();

    float getExperience();

}
