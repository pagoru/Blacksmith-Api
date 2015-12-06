package net.darkaqua.blacksmith.api.render.model.json;

import net.darkaqua.blacksmith.api.util.Vector3i;

/**
 * Created by cout970 on 28/11/2015.
 */
public interface IModelRotation {

    Vector3i getOrigin();

    Axis getAxis();

    double getAngle();

    boolean rescale();

    enum Axis{
        X, Y, Z
    }
}
