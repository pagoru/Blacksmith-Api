package net.darkaqua.blacksmith.api.gui;

import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.inventory.IInventoryHandler;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.util.Vect2i;
import net.darkaqua.blacksmith.api.util.annotations.Implementable;

/**
 * Created by cout970 on 25/12/2015.
 */
@Implementable
public interface ISlotDefinition {

    int getSlotID();

    IInventoryHandler getInventory();

    Vect2i getPosition();

    void onSlotUpdate();

    int getMaxStackSize();

    boolean isItemValid(IItemStack stack);

    boolean canTakeStack(IPlayer player);
}
