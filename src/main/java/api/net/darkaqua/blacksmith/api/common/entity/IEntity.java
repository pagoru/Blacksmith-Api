package net.darkaqua.blacksmith.api.common.entity;

import net.darkaqua.blacksmith.api.common.storage.IDataCompound;
import net.darkaqua.blacksmith.api.common.util.raytrace.Cube;
import net.darkaqua.blacksmith.api.common.util.EntityRotation;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect2d;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;
import net.darkaqua.blacksmith.api.common.world.IWorld;

import java.util.UUID;

public interface IEntity {

    IWorld getWorld();

    void setWorld(IWorld w);

    void update();

    Vect3d getLookVector();

    Vect3d getPosition();

    void setPosition(Vect3d pos);

    Vect3d getMotion();

    void setMotion(Vect3d mot);

    void moveEntity(Vect3d mot);

    EntityRotation getEntityRotation();

    void setEntityRotation(EntityRotation rot);

    Cube getEntityBoundingBox();

    Cube getEntityCollisionBox();

    Vect2d getEntitySize();

    boolean isDead();

    void setDead();

    UUID getEntityUUID();

    void load(IDataCompound data);

    void save(IDataCompound data);

    IEntity getRiddingEntity();

    IEntity getRiddenByEntity();
}
