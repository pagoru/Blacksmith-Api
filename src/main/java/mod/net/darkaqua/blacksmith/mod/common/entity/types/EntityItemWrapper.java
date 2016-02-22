package net.darkaqua.blacksmith.mod.common.entity.types;

import net.darkaqua.blacksmith.api.common.entity.types.IEntityItem;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.mod.common.entity.EntityWrapper;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;

/**
 * Created by cout970 on 19/01/2016.
 */
public class EntityItemWrapper extends EntityWrapper implements IEntityItem {

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
