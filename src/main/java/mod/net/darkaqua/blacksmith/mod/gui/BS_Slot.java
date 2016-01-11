package net.darkaqua.blacksmith.mod.gui;

import net.darkaqua.blacksmith.api.gui.ISlotDefinition;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by cout970 on 28/12/2015.
 */
public class BS_Slot extends Slot {

    private ISlotDefinition def;

    public BS_Slot(ISlotDefinition slot) {
        super(MCInterface.toInventory(slot.getInventory()), slot.getSlotID(), slot.getPosition().getX(), slot.getPosition().getY());
        def = slot;
    }

    public ISlotDefinition getDefinition() {
        return def;
    }

    public void onSlotChanged(){
        super.onSlotChanged();
        def.onSlotUpdate();
    }

    public int getSlotStackLimit(){
        return def.getMaxStackSize();
    }

    public boolean isItemValid(ItemStack stack){
        return def.isItemValid(MCInterface.fromItemStack(stack));
    }

    public boolean canTakeStack(EntityPlayer playerIn){
        return def.canTakeStack(MCInterface.toPlayer(playerIn));
    }
}
