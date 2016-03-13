package net.darkaqua.blacksmith.api.common.util.raytrace;

import net.darkaqua.blacksmith.api.common.entity.IEntity;
import net.darkaqua.blacksmith.api.common.util.Direction;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3i;

/**
 * Created by cout970 on 22/01/2016.
 */
public class RayTraceResult {

    protected ResultType type;
    protected Vect3d hit;
    //block hit
    protected Vect3i position;
    protected Direction side;
    //entity hit
    protected IEntity entity;
    protected Object extraData;

    protected RayTraceResult(ResultType type, Vect3d hit) {
        this.type = type;
        this.hit = hit.copy();
    }

    public RayTraceResult(Vect3d hit, Direction side, Vect3i position) {
        this(ResultType.BLOCK, hit);
        this.side = side;
        this.position = position == null ? Vect3i.nullVector() : position;
    }

    public RayTraceResult(Vect3d hit, IEntity entity) {
        this(ResultType.ENTITY, hit);
        this.entity = entity;
    }

    public RayTraceResult(Vect3d hit) {
        this(ResultType.MISS, hit);
    }

    public void setExtraData(Object extraData) {
        this.extraData = extraData;
    }

    public ResultType getType() {
        return type;
    }

    public Vect3d getHit() {
        return hit;
    }

    public Vect3i getPosition() {
        return position;
    }

    public Direction getSide() {
        return side;
    }

    public IEntity getEntity() {
        return entity;
    }

    public Object getExtraData() {
        return extraData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RayTraceResult)) return false;

        RayTraceResult that = (RayTraceResult) o;

        if (type != that.type) return false;
        if (hit != null ? !hit.equals(that.hit) : that.hit != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (side != that.side) return false;
        if (entity != null ? !entity.equals(that.entity) : that.entity != null) return false;
        return !(extraData != null ? !extraData.equals(that.extraData) : that.extraData != null);

    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (hit != null ? hit.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (side != null ? side.hashCode() : 0);
        result = 31 * result + (entity != null ? entity.hashCode() : 0);
        result = 31 * result + (extraData != null ? extraData.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RayTraceResult{" +
                "type=" + type +
                ", hit=" + hit +
                ", position=" + position +
                ", sides=" + side +
                ", entity=" + entity +
                ", extraData=" + extraData +
                '}';
    }

    public enum ResultType{
        MISS,
        BLOCK,
        ENTITY
    }
}
