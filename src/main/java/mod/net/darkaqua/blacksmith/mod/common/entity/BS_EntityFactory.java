package net.darkaqua.blacksmith.mod.common.entity;

import net.darkaqua.blacksmith.api.common.entity.EntityFactory;
import net.darkaqua.blacksmith.api.common.entity.IEntity;
import net.darkaqua.blacksmith.api.common.entity.types.IEntityItem;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;
import net.darkaqua.blacksmith.api.common.world.IWorld;
import net.darkaqua.blacksmith.mod.common.entity.types.EntityItemWrapper;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
import net.minecraft.entity.Entity;

/**
 * Created by cout970 on 19/01/2016.
 */
public class BS_EntityFactory extends EntityFactory {

    public static void init(){
        INSTANCE = new BS_EntityFactory();
    }

    private BS_EntityFactory(){}

    @Override
    protected IEntityItem newEntityItem(IWorld world, Vect3d pos, IItemStack stack) {
        return new EntityItemWrapper(new net.minecraft.entity.item.EntityItem(MCInterface.toWorld(world), pos.getX(), pos.getY(), pos.getZ(), MCInterface.toItemStack(stack)));
    }

    public static IEntity fromEntity(Entity entity) {
        if (entity instanceof net.minecraft.entity.item.EntityItem){
            return new EntityItemWrapper((net.minecraft.entity.item.EntityItem) entity);
        }
        return new EntityWrapper(entity);
    }
}
