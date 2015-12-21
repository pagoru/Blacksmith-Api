package net.darkaqua.blacksmith.api.inventory;

import net.darkaqua.blacksmith.api.item.IItem;
import net.darkaqua.blacksmith.api.storage.IDataCompound;

public interface IItemStack {

    IItem getItem();
    void setItem(IItem item);

    int getAmount();
    void setAmount(int amount);

    int getDamage();
    void setDamage(int damage);

    IDataCompound getDataCompound();
    void setDataCompound(IDataCompound cmp);

    String getUnlocalizedName();

    IItemStack copy();

    IItemStack split(int amount);

    String getDisplayName();

    Object getInternalItemStack();
}
