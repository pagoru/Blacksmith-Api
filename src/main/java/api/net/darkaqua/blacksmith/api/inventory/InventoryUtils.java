package net.darkaqua.blacksmith.api.inventory;

import net.darkaqua.blacksmith.api.storage.IDataCompound;

/**
 * Created by cout970 on 20/12/2015.
 */
public final class InventoryUtils {

    private InventoryUtils(){}

    public static boolean areExactlyEqual(IItemStack a, IItemStack b){
        if (a == b)return true;
        if (a == null || b == null)return false;
        if(a.getItem().equals(b.getItem()) && a.getAmount() == b.getAmount() && a.getDamage() == b.getDamage()){
            return areDataCompoundEqual(a.getDataCompound(), b.getDataCompound());
        }
        return false;
    }

    public static boolean areDataCompoundEqual(IDataCompound a, IDataCompound b){
        if (a == b)return true;
        if (a == null || b == null)return false;
        return a.equals(b);
    }

    public static boolean areOreDictEquivalent(IItemStack a, IItemStack b) {
        return false;
    }
}
