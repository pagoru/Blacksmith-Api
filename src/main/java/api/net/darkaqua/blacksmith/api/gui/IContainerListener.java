package net.darkaqua.blacksmith.api.gui;

import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.minecraft.inventory.Container;

import java.util.List;

/**
 * Created by cout970 on 25/12/2015.
 */
public interface IContainerListener {

    void updateInventory(IContainer containerToSend, List<IItemStack> inventory);

    void sendSlotContents(IContainer containerToSend, int slotInd, IItemStack stack);

    void sendUpdate(Container containerIn, int varToUpdate, short newValue);
}
