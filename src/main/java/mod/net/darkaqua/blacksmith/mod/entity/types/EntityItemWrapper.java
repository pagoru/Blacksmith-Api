package net.darkaqua.blacksmith.mod.entity.types;

import net.darkaqua.blacksmith.api.entity.types.EntityItem;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.mod.entity.EntityWrapper;
import net.darkaqua.blacksmith.mod.util.MCInterface;

/**
 * Created by cout970 on 19/01/2016.
 */
public class EntityItemWrapper extends EntityWrapper implements EntityItem{

    private net.minecraft.entity.item.EntityItem entityItem;

    public EntityItemWrapper(net.minecraft.entity.item.EntityItem ent) {
        super(ent);
        entityItem = ent;
    }

    public net.minecraft.entity.item.EntityItem getEntityItem() {
        return entityItem;
    }

    @Override
    public IItemStack getItemStack() {
        return MCInterface.fromItemStack(entityItem.getEntityItem());
    }

    @Override
    public void setItemStack(IItemStack stack) {
        entityItem.setEntityItemStack(MCInterface.toItemStack(stack));
    }
}
