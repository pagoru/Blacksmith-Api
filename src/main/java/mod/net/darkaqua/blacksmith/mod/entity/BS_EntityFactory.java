package net.darkaqua.blacksmith.mod.entity;

import net.darkaqua.blacksmith.api.entity.EntityFactory;
import net.darkaqua.blacksmith.api.entity.types.EntityItem;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.api.world.IWorld;
import net.darkaqua.blacksmith.mod.entity.types.EntityItemWrapper;
import net.darkaqua.blacksmith.mod.util.MCInterface;

/**
 * Created by cout970 on 19/01/2016.
 */
public class BS_EntityFactory extends EntityFactory {

    public static void init(){
        INSTANCE = new BS_EntityFactory();
    }

    private BS_EntityFactory(){}

    @Override
    protected EntityItem newEntityItem(IWorld world, Vect3d pos, IItemStack stack) {
        return new EntityItemWrapper(new net.minecraft.entity.item.EntityItem(MCInterface.toWorld(world), pos.getX(), pos.getY(), pos.getZ(), MCInterface.toItemStack(stack)));
    }
}
