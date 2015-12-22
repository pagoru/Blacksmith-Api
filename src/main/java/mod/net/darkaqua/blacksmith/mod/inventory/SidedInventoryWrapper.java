package net.darkaqua.blacksmith.mod.inventory;

import net.darkaqua.blacksmith.api.inventory.IInventoryHandler;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.inventory.InventoryUtils;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.inventory.ISidedInventory;

/**
 * Created by cout970 on 21/12/2015.
 */
public class SidedInventoryWrapper implements IInventoryHandler {

    private ISidedInventory inv;

    public SidedInventoryWrapper(ISidedInventory inv) {
        this.inv = inv;
    }

    public ISidedInventory getInventory() {
        return inv;
    }

    @Override
    public int getSlots(Direction side) {
        return inv.getSlotsForFace(MCInterface.toEnumFacing(side)).length;
    }

    @Override
    public IItemStack getStackInSlot(Direction side, int slot) {
        int[] slots = inv.getSlotsForFace(MCInterface.toEnumFacing(side));
        return MCInterface.fromItemStack(inv.getStackInSlot(slots[slot]));
    }

    @Override
    public void setStackInSlot(Direction side, int slot, IItemStack stack) {
        int[] slots = inv.getSlotsForFace(MCInterface.toEnumFacing(side));
        inv.setInventorySlotContents(slots[slot], MCInterface.toItemStack(stack));
    }

    @Override
    public IItemStack insertItemStack(Direction side, int m, IItemStack stack, boolean simulated) {
        if (stack == null)
            return null;
        int[] options = inv.getSlotsForFace(MCInterface.toEnumFacing(side));
        int slot = options[m];

        if(!inv.canInsertItem(slot, MCInterface.toItemStack(stack), MCInterface.toEnumFacing(side))){
            return stack;
        }

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
    public IItemStack extractItemStack(Direction side, int m, int amount, boolean simulated) {

        int[] options = inv.getSlotsForFace(MCInterface.toEnumFacing(side));
        int slot = options[m];

        IItemStack storage = MCInterface.fromItemStack(inv.getStackInSlot(slot));
        if (storage == null || amount <= 0){
            return null;
        }

        if(!inv.canExtractItem(slot, inv.getStackInSlot(slot), MCInterface.toEnumFacing(side))){
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
