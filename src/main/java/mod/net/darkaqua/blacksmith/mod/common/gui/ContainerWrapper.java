package net.darkaqua.blacksmith.mod.common.gui;

import net.darkaqua.blacksmith.api.common.entity.IPlayer;
import net.darkaqua.blacksmith.api.common.gui.IContainer;
import net.darkaqua.blacksmith.api.common.gui.IContainerListener;
import net.darkaqua.blacksmith.api.common.gui.IGuiDefinition;
import net.darkaqua.blacksmith.api.common.gui.ISlotDefinition;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
import net.minecraft.inventory.ICrafting;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cout970 on 11/01/2016.
 */
public class ContainerWrapper implements IContainer {

    private BS_Container cont;

    public ContainerWrapper(BS_Container cont) {
        this.cont = cont;
    }

    public BS_Container getContainer() {
        return cont;
    }

    @Override
    public void addSlot(ISlotDefinition slot) {
        cont.addSlot(MCInterface.toSlot(slot));
    }

    @Override
    public List<IContainerListener> getListeners() {
        //TODO
        return new LinkedList<>();
    }

    @Override
    public void addListener(IContainerListener listener) {
        cont.addListener(listener);
    }

    @Override
    public void removeListener(IContainerListener listener) {
        cont.removeListener(listener);
    }

    @Override
    public void sendToAllListeners(int id, short value) {
        for (ICrafting c : cont.getCrafters()){
            c.sendProgressBarUpdate(cont, id, value);
        }
    }

    @Override
    public List<ISlotDefinition> getSlots() {
        return cont.inventorySlots.stream().map(MCInterface::fromSlot).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public int getSlotCount() {
        return cont.inventorySlots.size();
    }

    @Override
    public List<IItemStack> getInventoryContents() {
        return cont.inventoryItemStacks.stream().map(MCInterface::fromItemStack).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public ISlotDefinition getSlot(int slotId) {
        return MCInterface.fromSlot(cont.getSlot(slotId));
    }

    @Override
    public void putStackInSlot(int slotID, IItemStack stack) {
        cont.putStackInSlot(slotID, MCInterface.toItemStack(stack));
    }

    @Override
    public void onContainerClose(IPlayer player) {
        cont.getGuiDefinition().onContainerClose(player);
    }

    @Override
    public void detectAndSendChanges() {
        cont.getGuiDefinition().detectAndSendChanges();
    }

    @Override
    public IGuiDefinition getDefinition() {
        return cont.getGuiDefinition();
    }
}
