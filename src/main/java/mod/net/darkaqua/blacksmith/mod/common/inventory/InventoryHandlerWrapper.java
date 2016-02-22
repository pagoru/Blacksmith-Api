package net.darkaqua.blacksmith.mod.common.inventory;

import net.darkaqua.blacksmith.api.common.inventory.IInventoryHandler;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IChatComponent;

/**
 * Created by cout970 on 21/12/2015.
 */
public class InventoryHandlerWrapper implements IInventory {

    private IInventoryHandler inv;

    public InventoryHandlerWrapper(IInventoryHandler inv) {
        this.inv = inv;
    }

    public IInventoryHandler getInventory() {
        return inv;
    }


    @Override
    public int getSizeInventory() {
        return inv.getSlots();
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return MCInterface.toItemStack(inv.getStackInSlot(index));
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        IItemStack ret = inv.extractItemStack(index, count, false);
        return MCInterface.toItemStack(ret);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return decrStackSize(index, 64);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        inv.extractItemStack(index, Integer.MAX_VALUE, false);
        inv.insertItemStack(index, MCInterface.fromItemStack(stack), false);
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void markDirty() {
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {
    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {}

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public IChatComponent getDisplayName() {
        return null;
    }
}
