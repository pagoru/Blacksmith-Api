package net.darkaqua.blacksmith.api.inventory;

import net.darkaqua.blacksmith.api.storage.IDataCompound;

/**
 * Created by cout970 on 20/12/2015.
 */
public final class InventoryUtils {

    private InventoryUtils() {
    }

    public static boolean areExactlyEqual(IItemStack a, IItemStack b) {
        return a == b || !(a == null || b == null)
                && a.getItem().equals(b.getItem())
                && a.getAmount() == b.getAmount()
                && a.getDamage() == b.getDamage()
                && areDataCompoundEqual(a.getDataCompound(), b.getDataCompound());
    }

    public static boolean areDataCompoundEqual(IDataCompound a, IDataCompound b) {
        return a == b || !(a == null || b == null) && a.equals(b);
    }

    public static boolean areOreDictEquivalent(IItemStack a, IItemStack b) {
        return false;
    }
}
