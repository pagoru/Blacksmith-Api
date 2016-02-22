package net.darkaqua.blacksmith.api.common.inventory;

import net.darkaqua.blacksmith.api.Game;
import net.darkaqua.blacksmith.api.common.storage.IDataCompound;

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
        List<String> namesA = Game.getCommonHandler().getOreDictionary().getNames(a);
        List<String> namesB = Game.getCommonHandler().getOreDictionary().getNames(b);
        return namesA.stream().anyMatch(namesB::contains);
    }

    public static boolean canInsertSomething(IInventoryHandler inv, int slot, IItemStack stack) {
        IItemStack result = inv.insertItemStack(slot, stack, true);
        return !areExactlyEqual(stack, result);
    }

    public static boolean insertAllInInventory(IInventoryHandler inv, IItemStack stack) {
        if (canInsertAllInInventory(inv, stack)) {
            if (stack == null)
                return true;
            for (int i = 0; i < inv.getSlots(); i++) {
                stack = inv.insertItemStack(i, stack, false);
                if (stack == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean canInsertAllInInventory(IInventoryHandler inv, IItemStack stack) {
        if (inv == null)
            return false;
        if (stack == null)
            return true;

        for (int i = 0; i < inv.getSlots(); i++) {
            stack = inv.insertItemStack(i, stack, true);
            if (stack == null) {
                return true;
            }
        }
        return false;
    }
}
