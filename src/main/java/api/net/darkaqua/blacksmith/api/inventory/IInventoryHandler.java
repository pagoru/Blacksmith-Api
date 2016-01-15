package net.darkaqua.blacksmith.api.inventory;

import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.annotations.Implementable;

/**
 * Created by cout970 on 19/12/2015.
 * Credits to rwtema for the idea:
 * https://github.com/rwtema/MinecraftForge/blob/deab3bf4a76302ff5c935684374a2cbf013cd276/src/main/java/net/minecraftforge/items/IItemHandler.java
 * <p>
 * Direction can be null in all methods, because vanilla
 */
@Implementable
public interface IInventoryHandler {
    /**
     * Gets the number of slot that are accessible from this side
     */
    int getSlots(Direction side);

    /**
     * Returns the content of a slot on a given side
     */
    IItemStack getStackInSlot(Direction side, int slot);

    /**
     * Sets the content of the slot to stack
     */
    void setStackInSlot(Direction side, int slot, IItemStack stack);

    default IItemStack insertItemStack(Direction side, int slot, IItemStack stack, boolean simulated) {
        if (stack == null)
            return null;

        if (getStackInSlot(null, slot) == null) {
            int capacity = Math.min(64, stack.getItem().getMaxStackSize(stack));
            if (capacity >= stack.getAmount()) {
                if (!simulated) {
                    setStackInSlot(null, slot, stack.copy());
                }
                return null;
            } else {
                if (!simulated) {
                    IItemStack insert = stack.copy();
                    insert.setAmount(capacity);
                    setStackInSlot(null, slot, insert);
                    IItemStack copy = stack.copy();
                    copy.setAmount(copy.getAmount() - capacity);
                    return copy;
                } else {
                    IItemStack copy = stack.copy();
                    copy.setAmount(copy.getAmount() - capacity);
                    return copy;
                }
            }
        } else if (InventoryUtils.areExactlyEqual(getStackInSlot(null, slot), stack)) {
            int capacity = Math.min(64, stack.getItem().getMaxStackSize(stack));
            int space = capacity - getStackInSlot(null, slot).getAmount();
            if (space >= stack.getAmount()) {
                if (!simulated) {
                    IItemStack copy = stack.copy();
                    copy.setAmount(copy.getAmount() + getStackInSlot(null, slot).getAmount());
                    setStackInSlot(null, slot, copy);
                }
                return null;
            } else {
                if (!simulated) {
                    IItemStack copy = stack.copy();
                    copy.setAmount(copy.getAmount() - space);
                    getStackInSlot(null, slot).setAmount(capacity);
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

    /**
     * Extract an amount of items from a slot on a given side, if simulated is true, no changes will be made.
     */
    default IItemStack extractItemStack(Direction side, int slot, int amount, boolean simulated) {
        IItemStack storage = getStackInSlot(null, slot);
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
                setStackInSlot(null, slot, null);
            }
            return storage.copy();
        }
    }


}
