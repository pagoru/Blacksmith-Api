package net.darkaqua.blacksmith.api.common.entity.types;

import net.darkaqua.blacksmith.api.common.entity.IEntity;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;

/**
 * Created by cout970 on 19/01/2016.
 */
public interface IEntityItem extends IEntity {

    IItemStack getItemStack();
    void setItemStack(IItemStack stack);
}
