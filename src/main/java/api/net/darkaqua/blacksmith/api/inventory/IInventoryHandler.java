package net.darkaqua.blacksmith.api.inventory;

import net.darkaqua.blacksmith.api.intermod.IInterfaceIdentifier;
import net.darkaqua.blacksmith.api.intermod.InterfaceIdentifierHolder;
import net.darkaqua.blacksmith.api.util.annotations.Implementable;

/**
 * Created by cout970 on 19/12/2015.
 */
@Implementable
public interface IInventoryHandler {

    @InterfaceIdentifierHolder(IInventoryHandler.class)
    IInterfaceIdentifier<IInventoryHandler> IDENTIFIER = null;

    /**
     * Gets the number of slots
     */
    int getSlots();

    /**
     * Returns the content of a slot
     */
    IItemStack getStackInSlot(int slot);

    /**
     * Inserts an amount of items into a slot, if simulated is true, no changes will be made.
     * Returns the excess items that cannot fit in the slot
     */
    IItemStack insertItemStack(int slot, IItemStack stack, boolean simulated);

    /**
     * Extract an amount of items from a slot, if simulated is true, no changes will be made.
     * Returns the items extracted
     */
    IItemStack extractItemStack(int slot, int amount, boolean simulated) ;
}
