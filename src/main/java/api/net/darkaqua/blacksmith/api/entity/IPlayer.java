package net.darkaqua.blacksmith.api.entity;

import net.darkaqua.blacksmith.api.inventory.IInventoryProvider;
import net.darkaqua.blacksmith.api.inventory.IItemStack;

/**
 * Created by cout970 on 15/11/2015.
 */
public interface IPlayer extends ILivingEntity, IInventoryProvider {

    IItemStack getSelectedItemStack();

    void setSelectedItemStack(IItemStack stack);
}
