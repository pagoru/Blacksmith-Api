package net.darkaqua.blacksmith.mod.container;

import net.darkaqua.blacksmith.api.container.IContainer;
import net.darkaqua.blacksmith.api.container.IContainerDefinition;
import net.darkaqua.blacksmith.api.container.IContainerListener;
import net.darkaqua.blacksmith.api.container.ISlotDefinition;
import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by cout970 on 28/12/2015.
 */
public class ContainerComponent implements IContainer {

    private BS_Container cont;
    private IContainerDefinition def;

    public ContainerComponent(IContainerDefinition def) {
        this.def = def;
    }

    public void bind(BS_Container cont) {
        this.cont = cont;
        def.initContainer(this);
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
    public List<ISlotDefinition> getSlots() {
        List<ISlotDefinition> list = new LinkedList<>();
        for(Slot s : cont.inventorySlots){
            list.add(MCInterface.fromSlot(s));
        }
        return list;
    }

    @Override
    public int getSlotCount() {
        return cont.inventorySlots.size();
    }

    @Override
    public List<IItemStack> getInventoryContents() {
        List<IItemStack> list = new LinkedList<>();
        for(ItemStack s : cont.inventoryItemStacks){
            list.add(MCInterface.fromItemStack(s));
        }
        return list;
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
    public void onClose(IPlayer player) {
        def.onClose(player);
    }

    @Override
    public void detectAndSendChanges() {
        def.detectAndSendChanges();
    }

    @Override
    public IContainerDefinition getDefinition() {
        return def;
    }
}
