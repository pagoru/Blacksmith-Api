package net.darkaqua.blacksmith.mod.inventory;

import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.item.IItem;
import net.darkaqua.blacksmith.api.storage.IDataCompound;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.item.ItemStack;

/**
 * Created by cout970 on 08/11/2015.
 */
public class ItemStackWrapper implements IItemStack{

    private ItemStack stack;

    public ItemStackWrapper(ItemStack stack){
        this.stack = stack;
    }

    public ItemStack getItemStack(){
        return stack;
    }

    @Override
    public IItem getItem() {
        return MCInterface.fromItem(stack.getItem());
    }

    @Override
    public void setItem(IItem item) {
        stack.setItem(MCInterface.toItem(item));
    }

    @Override
    public int getAmount() {
        return stack.stackSize;
    }

    @Override
    public void setAmount(int amount) {
        stack.stackSize = amount;
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
    public void setDamage(int damage) {
    stack.setItemDamage(damage);
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
    public void setDataCompound(IDataCompound cmp) {
        stack.setTagCompound(MCInterface.toNBTCompound(cmp));
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
    public Object getInternalItemStack() {
        return stack;
    }
}
