package net.darkaqua.blacksmith.api.common.inventory.defaults;

import net.darkaqua.blacksmith.api.common.inventory.*;
import net.darkaqua.blacksmith.api.common.storage.DataElementFactory;
import net.darkaqua.blacksmith.api.common.storage.IDataCompound;
import net.darkaqua.blacksmith.api.common.storage.IDataList;

/**
 * Created by cout970 on 20/12/2015.
 */
public class SimpleInventoryHandler implements IInternalInventoryHandler {

    protected IItemStack[] inventory;
    protected int maxStackSize = 64;

    public SimpleInventoryHandler(int slots, int stackSize){
        this(slots);
        maxStackSize = stackSize;
    }

    public SimpleInventoryHandler(int slots) {
        inventory = new IItemStack[slots];
    }

    @Override
    public int getSlots() {
        return inventory.length;
    }

    @Override
    public IItemStack getStackInSlot(int slot) {
        return inventory[slot];
    }

    @Override
    public IItemStack insertItemStack(int slot, IItemStack stack, boolean simulated) {
        if (stack == null)
            return null;

        if (getStackInSlot(slot) == null) {
            int capacity = Math.min(maxStackSize, stack.getItem().getMaxStackSize(stack));
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
            int capacity = Math.min(maxStackSize, stack.getItem().getMaxStackSize(stack));
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

    public void load(IDataCompound tag, String key) {
        IDataList list = tag.getDataList(key);
        for (int i = 0; i < list.getSize(); i++) {
            IDataCompound cmp = list.getDataCompound(i);
            int slot = cmp.getByte("Slot") & 0xFF;
            if (slot >= 0 && slot < getSlots()) {
                setStackInSlot(slot, ItemStackFactory.loadItemStack(cmp));
            }
        }
    }

    public void save(IDataCompound tag, String key) {
        IDataList list = DataElementFactory.createDataList();
        for (int i = 0; i < getSlots(); i++) {
            if (getStackInSlot(i) != null) {
                IDataCompound data = DataElementFactory.createDataCompound();
                data.setByte("Slot", (byte) i);
                ItemStackFactory.saveItemStack(data, getStackInSlot(i));
                list.addDataCompound(data);
            }
        }
        tag.setDataElement(key, list);
    }

    @Override
    public void setStackInSlot(int slot, IItemStack stack) {
        inventory[slot] = stack;
    }
}
