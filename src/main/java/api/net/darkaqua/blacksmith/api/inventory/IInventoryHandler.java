package net.darkaqua.blacksmith.api.inventory;

import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.annotations.Implementable;

/**
 * Created by cout970 on 19/12/2015.
 * Credits to rwtema for the idea:
 * https://github.com/rwtema/MinecraftForge/blob/deab3bf4a76302ff5c935684374a2cbf013cd276/src/main/java/net/minecraftforge/items/IItemHandler.java
 *
 * Direction can be null in all methods, because vanilla
 */
@Implementable
public interface IInventoryHandler {

    /**
     * Gets the number of slot that are accessible from this side
     */
    int getSlots(Direction side);

    /**
     * Returns the content of a slot on a given side
     */
    IItemStack getStackInSlot(Direction side, int slot);

    /**
     * Sets the content of the slot to stack
     */
    void setStackInSlot(Direction side, int slot, IItemStack stack);

    /**
     * Inserts an IItemStack into an slot at a given side, if simulated is true, no changes will be made.
     * The return IItemStack should be the remaining items that the slot cannot accept
     */
    IItemStack insertItemStack(Direction side, int slot, IItemStack stack, boolean simulated);

    /**
     * Extract an amount of items from a slot on a given side, if simulated is true, no changes will be made.
     */
    IItemStack extractItemStack(Direction side, int slot, int amount, boolean simulated);


}
