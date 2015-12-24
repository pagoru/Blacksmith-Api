package net.darkaqua.blacksmith.api.recipe;

import net.darkaqua.blacksmith.api.inventory.IItemStack;

/**
 * Created by cout970 on 23/12/2015.
 */
public interface ICraftingGrid {

    int getSlotCount();

    IItemStack getStackInSlot(int slot);

    void setStackInSlot(IItemStack stack, int slot);

    int getHeight();
    int getWidth();

    int getMaxStackSize();
}
