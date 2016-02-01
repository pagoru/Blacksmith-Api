package net.darkaqua.blacksmith.api.inventory;

import net.darkaqua.blacksmith.api.item.IItem;
import net.darkaqua.blacksmith.api.storage.IDataCompound;

public interface IItemStack {

    IItem getItem();

    IItemStack setItem(IItem item);

    int getAmount();

    IItemStack setAmount(int amount);

    int getMaxAmount();

    int getDamage();

    IItemStack setDamage(int damage);

    int getMaxDamage();

    IDataCompound getDataCompound();

    IItemStack setDataCompound(IDataCompound cmp);

    String getUnlocalizedName();

    IItemStack copy();

    IItemStack split(int amount);

    String getDisplayName();

    Object getInternalItemStack();
}
