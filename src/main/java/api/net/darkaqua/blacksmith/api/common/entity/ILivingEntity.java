package net.darkaqua.blacksmith.api.common.entity;

/**
 * Created by cout970 on 16/12/2015.
 */
public interface ILivingEntity extends IEntity {


    float getMaxHealth();

    float getHealth();

    void setHealth(float health);

}
