package net.darkaqua.blacksmith.api.common.inventory;

/**
 * Created by cout970 on 22/01/2016.
 */
public interface IInternalInventoryHandler extends IInventoryHandler {

    void setStackInSlot(int slot, IItemStack stack);
}
