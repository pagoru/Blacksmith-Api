package net.darkaqua.blacksmith.api.container;

import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.inventory.IItemStack;

import java.util.List;

/**
 * Created by cout970 on 23/12/2015.
 */
public interface IContainer {

    void addSlot(ISlotDefinition slot);

    List<IContainerListener> getListeners();

    void addListener(IContainerListener listener);

    void removeListener(IContainerListener listener);

    List<ISlotDefinition> getSlots();

    int getSlotCount();

    List<IItemStack> getInventoryContents();

    ISlotDefinition getSlot(int slotId);

    void putStackInSlot(int slotID, IItemStack stack);

    void onClose(IPlayer player);

    void detectAndSendChanges();

    IContainerDefinition getDefinition();
}
