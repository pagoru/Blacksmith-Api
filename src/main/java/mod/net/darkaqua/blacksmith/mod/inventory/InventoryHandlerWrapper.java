package net.darkaqua.blacksmith.mod.inventory;

import net.darkaqua.blacksmith.api.inventory.IInventoryHandler;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.inventory.InventoryUtils;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;

/**
 * Created by cout970 on 21/12/2015.
 */
public class InventoryHandlerWrapper implements ISidedInventory {

    private IInventoryHandler inv;

    public InventoryHandlerWrapper(IInventoryHandler inv) {
        this.inv = inv;
    }

    public IInventoryHandler getInventory() {
        return inv;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        int slots = inv.getSlots(MCInterface.fromEnumFacing(side));
        int[] res = new int[slots];
        for (int i = 0; i < slots; i++) {
            res[i] = i;
        }
        return res;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        IItemStack stack = MCInterface.fromItemStack(itemStackIn);
        IItemStack stack2 = inv.insertItemStack(MCInterface.fromEnumFacing(direction), index, stack, true);
        return !InventoryUtils.areExactlyEqual(stack, stack2);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return inv.extractItemStack(MCInterface.fromEnumFacing(direction), index, itemStackIn.stackSize, true) != null;
    }

    @Override
    public int getSizeInventory() {
        return inv.getSlots(null);
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return MCInterface.toItemStack(inv.getStackInSlot(null, index));
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        IItemStack ret = inv.extractItemStack(null, index, count, false);
        return MCInterface.toItemStack(ret);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        inv.setStackInSlot(null, index, MCInterface.fromItemStack(stack));
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
    public void clear() {
        for (Direction d : Direction.values()) {
            for (int i = 0; i < inv.getSlots(d); i++) {
                inv.setStackInSlot(d, i, null);
            }
        }
    }

    @Override
    public String getCommandSenderName() {
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
