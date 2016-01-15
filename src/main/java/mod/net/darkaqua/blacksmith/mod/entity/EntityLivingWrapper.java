package net.darkaqua.blacksmith.mod.entity;

import net.darkaqua.blacksmith.api.entity.ILivingEntity;
import net.minecraft.entity.EntityLivingBase;

/**
 * Created by cout970 on 13/01/2016.
 */
public class EntityLivingWrapper extends EntityWrapper implements ILivingEntity{

    private EntityLivingBase living;

    public EntityLivingWrapper(EntityLivingBase ent) {
        super(ent);
        this.living = ent;
    }

    public EntityLivingBase getLivingEntity() {
        return living;
    }

    @Override
    public float getMaxHealth() {
        return living.getMaxHealth();
    }

    @Override
    public float getHealth() {
        return living.getHealth();
    }

    @Override
    public void setHealth(float health) {
        living.setHealth(health);
    }
}
