package net.darkaqua.blacksmith.mod.entity;

import net.darkaqua.blacksmith.api.entity.IEntity;
import net.darkaqua.blacksmith.api.storage.IDataCompound;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.util.EntityRotation;
import net.darkaqua.blacksmith.api.util.Vect2d;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.api.world.IWorld;
import net.minecraft.entity.Entity;

import java.util.UUID;

/**
 * Created by cout970 on 08/11/2015.
 */
public class EntityWrapper implements IEntity {

    private Entity entity;

    public EntityWrapper(Entity ent) {
        this.entity = ent;
    }

    public Entity getEntity() {
        return entity;
    }

    //TODO

    @Override
    public IWorld getWorld() {
        return null;
    }

    @Override
    public void setWorld(IWorld w) {

    }

    @Override
    public void update() {

    }

    @Override
    public Vect3d getLookVector() {
        return null;
    }

    @Override
    public Vect3d getPosition() {
        return null;
    }

    @Override
    public void setPosition(Vect3d pos) {

    }

    @Override
    public Vect3d getMotion() {
        return null;
    }

    @Override
    public void setMotion(Vect3d mot) {

    }

    @Override
    public void moveEntity(Vect3d mot) {

    }

    @Override
    public EntityRotation getEntityRotation() {
        return null;
    }

    @Override
    public void setEntityRotation(EntityRotation rot) {

    }

    @Override
    public Cube getEntityBoundingBox() {
        return null;
    }

    @Override
    public Cube getEntityCollisionBox() {
        return null;
    }

    @Override
    public Vect2d getEntitySize() {
        return null;
    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public void setDead() {

    }

    @Override
    public UUID getEntityUUID() {
        return null;
    }

    @Override
    public void load(IDataCompound data) {

    }

    @Override
    public void save(IDataCompound data) {

    }

    @Override
    public IEntity getRiddingEntity() {
        return null;
    }

    @Override
    public IEntity getRiddenByEntity() {
        return null;
    }
}
