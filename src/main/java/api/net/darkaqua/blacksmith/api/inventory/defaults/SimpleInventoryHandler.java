package net.darkaqua.blacksmith.api.inventory.defaults;

import net.darkaqua.blacksmith.api.inventory.*;
import net.darkaqua.blacksmith.api.storage.DataElementFactory;
import net.darkaqua.blacksmith.api.storage.IDataCompound;
import net.darkaqua.blacksmith.api.storage.IDataList;

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

        if (inventory[slot] == null) {
            int capacity = Math.min(maxStackSize, stack.getItem().getMaxStackSize(stack));
            if (capacity >= stack.getAmount()) {
                if (!simulated) {
                    inventory[slot] = stack.copy();
                }
                return null;
            } else {
                if (!simulated) {
                    IItemStack insert = stack.copy();
                    insert.setAmount(capacity);
                    inventory[slot] = insert;
                    IItemStack copy = stack.copy();
                    copy.setAmount(copy.getAmount() - capacity);
                    return copy;
                } else {
                    IItemStack copy = stack.copy();
                    copy.setAmount(copy.getAmount() - capacity);
                    return copy;
                }
            }
        } else if (InventoryUtils.areEqual(inventory[slot], stack)) {
            int capacity = Math.min(maxStackSize, stack.getItem().getMaxStackSize(stack));
            int space = capacity - inventory[slot].getAmount();
            if (space >= stack.getAmount()) {
                if (!simulated) {
                    IItemStack copy = stack.copy();
                    copy.setAmount(copy.getAmount() + inventory[slot].getAmount());
                    inventory[slot] = copy;
                }
                return null;
            } else {
                if (!simulated) {
                    IItemStack copy = stack.copy();
                    copy.setAmount(copy.getAmount() - space);
                    inventory[slot].setAmount(capacity);
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
        IItemStack storage = inventory[slot];
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
                inventory[slot] = null;
            }
            return storage.copy();
        }
    }

    public void load(IDataCompound tag, String key) {
        IDataList list = tag.getDataList(key);
        for (int i = 0; i < list.getSize(); i++) {
            IDataCompound cmp = list.getDataCompound(i);
            int slot = cmp.getByte("Slot") & 0xFF;
            if (slot >= 0 && slot < inventory.length) {
                inventory[slot] = ItemStackFactory.loadItemStack(cmp);
            }
        }
    }

    public void save(IDataCompound tag, String key) {
        IDataList list = DataElementFactory.createDataList();
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null) {
                IDataCompound data = DataElementFactory.createDataCompound();
                data.setByte("Slot", (byte) i);
                ItemStackFactory.saveItemStack(data, inventory[i]);
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
