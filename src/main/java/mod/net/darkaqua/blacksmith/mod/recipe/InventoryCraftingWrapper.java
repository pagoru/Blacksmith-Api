package net.darkaqua.blacksmith.mod.recipe;

import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.recipe.ICraftingGrid;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.inventory.InventoryCrafting;

/**
 * Created by cout970 on 23/12/2015.
 */
public class InventoryCraftingWrapper implements ICraftingGrid {

    private InventoryCrafting inv;

    public InventoryCraftingWrapper(InventoryCrafting inv) {
        this.inv = inv;
    }

    public InventoryCrafting getInventory() {
        return inv;
    }

    @Override
    public int getSlotCount() {
        return inv.getSizeInventory();
    }

    @Override
    public IItemStack getStackInSlot(int slot) {
        return MCInterface.fromItemStack(inv.getStackInSlot(slot));
    }

    @Override
    public void setStackInSlot(IItemStack stack, int slot) {
        inv.setInventorySlotContents(slot, MCInterface.toItemStack(stack));
    }

    @Override
    public int getHeight() {
        return inv.getHeight();
    }

    @Override
    public int getWidth() {
        return inv.getWidth();
    }

    @Override
    public int getMaxStackSize() {
        return inv.getInventoryStackLimit();
    }
}
