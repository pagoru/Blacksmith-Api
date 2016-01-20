package net.darkaqua.blacksmith.api.entity.types;

import net.darkaqua.blacksmith.api.entity.IEntity;
import net.darkaqua.blacksmith.api.inventory.IItemStack;

/**
 * Created by cout970 on 19/01/2016.
 */
public interface EntityItem extends IEntity {

    IItemStack getItemStack();
    void setItemStack(IItemStack stack);
}
