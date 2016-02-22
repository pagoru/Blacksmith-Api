package net.darkaqua.blacksmith.mod.common.inventory;

import net.darkaqua.blacksmith.api.common.inventory.IInternalInventoryHandler;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.common.inventory.InventoryUtils;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
import net.minecraft.inventory.IInventory;

/**
 * Created by cout970 on 21/12/2015.
 */
public class SimpleInventoryWrapper implements IInternalInventoryHandler {

    private IInventory inv;

    public SimpleInventoryWrapper(IInventory inv) {
        this.inv = inv;
    }

    public IInventory getInventory() {
        return inv;
    }

    @Override
    public int getSlots() {
        return inv.getSizeInventory();
    }

    @Override
    public IItemStack getStackInSlot(int slot) {
        return MCInterface.fromItemStack(inv.getStackInSlot(slot));
    }

    public void setStackInSlot(int slot, IItemStack stack) {
        inv.setInventorySlotContents(slot, MCInterface.toItemStack(stack));
    }
    @Override
    public IItemStack insertItemStack(int slot, IItemStack stack, boolean simulated) {
        if (stack == null)
            return null;

        if (getStackInSlot(slot) == null) {
            int capacity = Math.min(64, stack.getItem().getMaxStackSize(stack));
            if (capacity >= stack.getAmount()) {
                if (!simulated) {
                    setStackInSlot(slot, stack.copy());
                }
                return null;
            } else {
                if (!simulated) {
                    IItemStack insert = stack.copy();
                    insert.setAmount(capacity);
                    setStackInSlot(slot, insert);
                    IItemStack copy = stack.copy();
                    copy.setAmount(copy.getAmount() - capacity);
                    return copy;
                } else {
                    IItemStack copy = stack.copy();
                    copy.setAmount(copy.getAmount() - capacity);
                    return copy;
                }
            }
        } else if (InventoryUtils.areEqual(getStackInSlot(slot), stack)) {
            int capacity = Math.min(64, stack.getItem().getMaxStackSize(stack));
            int space = capacity - getStackInSlot(slot).getAmount();
            if (space >= stack.getAmount()) {
                if (!simulated) {
                    IItemStack copy = stack.copy();
                    copy.setAmount(copy.getAmount() + getStackInSlot(slot).getAmount());
                    setStackInSlot(slot, copy);
                }
                return null;
            } else {
                if (!simulated) {
                    IItemStack copy = stack.copy();
                    copy.setAmount(copy.getAmount() - space);
                    getStackInSlot(slot).setAmount(capacity);
                    return copy;
                } else {
                    IItemStack copy = stack.copy();
                    copy.setAmount(copy.getAmount() - space);
                    return copy;
                }
            }
        }
        return stack;
    }

    @Override
    public IItemStack extractItemStack(int slot, int amount, boolean simulated) {
        IItemStack storage = getStackInSlot(slot);
        if (storage == null || amount <= 0) {
            return null;
        }

        if (storage.getAmount() > amount) {
            IItemStack ret = storage.copy();
            if (!simulated) {
                storage.setAmount(storage.getAmount() - amount);
            }
            ret.setAmount(amount);
            return ret;
        } else {
            if (!simulated) {
                setStackInSlot(slot, null);
            }
            return storage.copy();
        }
    }

}
