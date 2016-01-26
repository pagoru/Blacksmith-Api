package net.darkaqua.blacksmith.api.entity;

import net.darkaqua.blacksmith.api.entity.types.IEntityItem;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.api.world.IWorld;

/**
 * Created by cout970 on 19/01/2016.
 */
public abstract class EntityFactory {

    protected static EntityFactory INSTANCE;

    public static IEntityItem createEntityItem(IWorld world, Vect3d pos, IItemStack stack){
        return INSTANCE.newEntityItem(world, pos, stack);
    }

    protected abstract IEntityItem newEntityItem(IWorld world, Vect3d pos, IItemStack stack);
}
