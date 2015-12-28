package net.darkaqua.blacksmith.mod.container;

import net.darkaqua.blacksmith.api.container.IContainerListener;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

/**
 * Created by cout970 on 27/12/2015.
 */
public class BS_Container extends Container {

    private ContainerComponent container;

    public BS_Container(ContainerComponent container) {
        this.container = container;
        container.bind(this);
    }

    public ContainerComponent getContainer() {
        return container;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return container.getDefinition().canInteractWith(MCInterface.toPlayer(playerIn));
    }

    public void addSlot(Slot s){
        addSlotToContainer(s);
    }

    public void addListener(IContainerListener listener) {
        //TODO
    }

    public void removeListener(IContainerListener listener) {
        //TODO
    }
}
