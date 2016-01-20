package net.darkaqua.blacksmith.api.entity;

import net.darkaqua.blacksmith.api.entity.types.EntityItem;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.api.world.IWorld;

/**
 * Created by cout970 on 19/01/2016.
 */
public abstract class EntityFactory {

    protected static EntityFactory INSTANCE;

    public EntityItem createEntityItem(IWorld world, Vect3d pos, IItemStack stack){
        return INSTANCE.newEntityItem(world, pos, stack);
    }

    protected abstract EntityItem newEntityItem(IWorld world, Vect3d pos, IItemStack stack);
}
