package net.darkaqua.blacksmith.api.common.gui.defaults;

import net.darkaqua.blacksmith.api.common.entity.IPlayer;
import net.darkaqua.blacksmith.api.common.gui.ISlotDefinition;
import net.darkaqua.blacksmith.api.common.inventory.IInventoryHandler;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect2i;

/**
 * Created by cout970 on 28/12/2015.
 */
public class DefaultSlotDefinition implements ISlotDefinition {

    protected int id;
    protected IInventoryHandler inventory;
    protected Vect2i position;

    public DefaultSlotDefinition(IInventoryHandler inventory, int id, Vect2i position) {
        this.id = id;
        this.inventory = inventory;
        this.position = position;
    }

    @Override
    public int getSlotID() {
        return id;
    }

    @Override
    public IInventoryHandler getInventory() {
        return inventory;
    }

    @Override
    public Vect2i getPosition() {
        return position;
    }

    @Override
    public void onSlotUpdate() {
    }

    @Override
    public int getMaxStackSize() {
        return 64;
    }

    @Override
    public boolean isItemValid(IItemStack stack) {
        return true;
    }

    @Override
    public boolean canTakeStack(IPlayer player) {
        return true;
    }
}
