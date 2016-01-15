package net.darkaqua.blacksmith.mod.inventory;

import net.darkaqua.blacksmith.api.inventory.IInventoryHandler;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.inventory.IInventory;

/**
 * Created by cout970 on 21/12/2015.
 */
public class SimpleInventoryWrapper implements IInventoryHandler {

    private IInventory inv;

    public SimpleInventoryWrapper(IInventory inv) {
        this.inv = inv;
    }

    public IInventory getInventory() {
        return inv;
    }

    @Override
    public int getSlots(Direction side) {
        return inv.getSizeInventory();
    }

    @Override
    public IItemStack getStackInSlot(Direction side, int slot) {
        return MCInterface.fromItemStack(inv.getStackInSlot(slot));
    }

    @Override
    public int getInventoryStackLimit() {
        return inv.getInventoryStackLimit();
    }

    @Override
    public void setStackInSlot(Direction side, int slot, IItemStack stack) {
        inv.setInventorySlotContents(slot, MCInterface.toItemStack(stack));
    }
}
