package net.darkaqua.blacksmith.mod.common.inventory;

import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.common.item.IItem;
import net.darkaqua.blacksmith.api.common.storage.IDataCompound;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
import net.minecraft.item.ItemStack;

/**
 * Created by cout970 on 08/11/2015.
 */
public class ItemStackWrapper implements IItemStack {

    private ItemStack stack;

    public ItemStackWrapper(ItemStack stack) {
        this.stack = stack;
    }

    public ItemStack getItemStack() {
        return stack;
    }

    @Override
    public IItem getItem() {
        return MCInterface.fromItem(stack.getItem());
    }

    @Override
    public IItemStack setItem(IItem item) {
        stack.setItem(MCInterface.toItem(item));
        return this;
    }

    @Override
    public int getAmount() {
        return stack.stackSize;
    }

    @Override
    public IItemStack setAmount(int amount) {
        stack.stackSize = amount;
        return this;
    }

    @Override
    public int getMaxAmount() {
        return stack.getMaxStackSize();
    }

    @Override
    public int getDamage() {
        return stack.getItemDamage();
    }

    @Override
    public IItemStack setDamage(int damage) {
        stack.setItemDamage(damage);
        return this;
    }

    @Override
    public int getMaxDamage() {
        return stack.getMaxDamage();
    }

    @Override
    public IDataCompound getDataCompound() {
        return MCInterface.fromNBTCompound(stack.getTagCompound());
    }

    @Override
    public IItemStack setDataCompound(IDataCompound cmp) {
        stack.setTagCompound(MCInterface.toNBTCompound(cmp));
        return this;
    }

    @Override
    public String getUnlocalizedName() {
        return stack.getUnlocalizedName();
    }

    @Override
    public IItemStack copy() {
        return MCInterface.fromItemStack(stack.copy());
    }

    @Override
    public IItemStack split(int amount) {
        return MCInterface.fromItemStack(stack.splitStack(amount));
    }

    @Override
    public String getDisplayName() {
        return stack.getDisplayName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemStackWrapper)) return false;

        ItemStackWrapper that = (ItemStackWrapper) o;
        return ItemStack.areItemStacksEqual(that.stack, stack);
    }

    @Override
    public int hashCode() {
        int hash = (stack.getItem().hashCode() * 31 + stack.stackSize) * 31 + stack.getItemDamage();
        if(stack.getTagCompound() != null){
            hash = hash * 31 + stack.getTagCompound().hashCode();
        }
        return hash;
    }

    @Override
    public String
    toString() {
        return "ItemStackWrapper{" +
                "stack=" + stack +
                '}';
    }

    @Override
    public Object getInternalItemStack() {
        return stack;
    }
}
