package net.darkaqua.blacksmith.mod.entity;

import net.darkaqua.blacksmith.api.entity.IEntity;
import net.minecraft.entity.Entity;

/**
 * Created by cout970 on 08/11/2015.
 */
public class EntityWrapper implements IEntity{

    private Entity entity;

    public EntityWrapper(Entity ent){
        this.entity = ent;
    }

    public Entity getEntity(){
        return entity;
    }
}
