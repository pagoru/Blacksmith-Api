package net.darkaqua.blacksmith.api.inventory;

import net.darkaqua.blacksmith.api.registry.StaticAccess;
import net.darkaqua.blacksmith.api.storage.IDataCompound;

import java.util.List;

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

    public static boolean areEqual(IItemStack a, IItemStack b) {
        return a == b || !(a == null || b == null)
                && a.getItem().equals(b.getItem())
                && a.getDamage() == b.getDamage()
                && areDataCompoundEqual(a.getDataCompound(), b.getDataCompound());
    }

    public static boolean areDataCompoundEqual(IDataCompound a, IDataCompound b) {
        return a == b || !(a == null || b == null) && a.equals(b);
    }

    public static boolean areOreDictEquivalent(IItemStack a, IItemStack b) {
        List<String> namesA = StaticAccess.GAME.getOreDictionary().getNames(a);
        List<String> namesB = StaticAccess.GAME.getOreDictionary().getNames(b);
        return namesA.stream().anyMatch(namesB::contains);
    }
}
