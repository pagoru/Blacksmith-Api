package net.darkaqua.blacksmith.mod.common.gui;

import net.darkaqua.blacksmith.api.common.gui.IContainerListener;
import net.darkaqua.blacksmith.api.common.gui.IGuiDefinition;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by cout970 on 27/12/2015.
 */
public class BS_Container extends Container {

    private ContainerWrapper wrapper;
    private IGuiDefinition def;

    public BS_Container(IGuiDefinition def) {
        this.def = def;
        this.wrapper = new ContainerWrapper(this);
        def.initContainer(wrapper);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return def.canInteractWith(MCInterface.toPlayer(playerIn));
    }

    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        //TODO this needs to be override to avoid shift click crash
        return null;
    }

    public void addSlot(Slot s) {
        addSlotToContainer(s);
    }

    public void addListener(IContainerListener listener) {
        //TODO
    }

    public void removeListener(IContainerListener listener) {
        //TODO
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        def.detectAndSendChanges();
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        def.receivedUpdate(id, (short) data);
    }

    public IGuiDefinition getGuiDefinition() {
        return def;
    }

    public List<ICrafting> getCrafters() {
        return crafters;
    }
}
