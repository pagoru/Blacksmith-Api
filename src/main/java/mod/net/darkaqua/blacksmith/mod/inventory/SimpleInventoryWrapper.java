package net.darkaqua.blacksmith.mod.inventory;

import net.darkaqua.blacksmith.api.inventory.IInventoryHandler;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.inventory.InventoryUtils;
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
    public void setStackInSlot(Direction side, int slot, IItemStack stack) {
        inv.setInventorySlotContents(slot, MCInterface.toItemStack(stack));
    }

    @Override
    public IItemStack insertItemStack(Direction side, int slot, IItemStack stack, boolean simulated) {
        if (stack == null)
            return null;

        if(inv.getStackInSlot(slot) == null){
            int capacity = Math.min(inv.getInventoryStackLimit(), stack.getItem().getMaxStackSize(stack));
            if(capacity >= stack.getAmount()){
                if (!simulated){
                    inv.setInventorySlotContents(slot, MCInterface.toItemStack(stack.copy()));
                }
                return null;
            }else{
                if (!simulated){
                    IItemStack insert = stack.copy();
                    insert.setAmount(capacity);
                    inv.setInventorySlotContents(slot, MCInterface.toItemStack(insert));
                    IItemStack copy = stack.copy();
                    copy.setAmount(copy.getAmount()-capacity);
                    return copy;
                }else{
                    IItemStack copy = stack.copy();
                    copy.setAmount(copy.getAmount()-capacity);
                    return copy;
                }
            }
        }else if(InventoryUtils.areExactlyEqual(MCInterface.fromItemStack(inv.getStackInSlot(slot)), stack)){
            int capacity = Math.min(inv.getInventoryStackLimit(), stack.getItem().getMaxStackSize(stack));
            int space = capacity-inv.getStackInSlot(slot).stackSize;
            if (space >= stack.getAmount()){
                if(!simulated){
                    IItemStack copy = stack.copy();
                    copy.setAmount(copy.getAmount()+inv.getStackInSlot(slot).stackSize);
                    inv.setInventorySlotContents(slot, MCInterface.toItemStack(copy));
                }
                return null;
            }else{
                if (!simulated){
                    IItemStack copy = stack.copy();
                    copy.setAmount(copy.getAmount()-space);
                    inv.getStackInSlot(slot).stackSize = capacity;
                    return copy;
                }else{
                    IItemStack copy = stack.copy();
                    copy.setAmount(copy.getAmount()-space);
                    return copy;
                }
            }
        }
        return stack;
    }

    @Override
    public IItemStack extractItemStack(Direction side, int slot, int amount, boolean simulated) {

        IItemStack storage = MCInterface.fromItemStack(inv.getStackInSlot(slot));
        if (storage == null || amount <= 0){
            return null;
        }

        if (storage.getAmount() > amount){
            IItemStack ret = storage.copy();
            if (!simulated){
                storage.setAmount(storage.getAmount()-amount);
            }
            ret.setAmount(amount);
            return ret;
        }else{
            if (!simulated){
                inv.setInventorySlotContents(slot, null);
            }
            return storage.copy();
        }
    }
}
