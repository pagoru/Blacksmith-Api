package net.darkaqua.blacksmith.mod.temp.old_json;

import net.darkaqua.blacksmith.api.util.Vect3i;

/**
 * Created by cout970 on 28/11/2015.
 */
public interface IJsonModelRotation {

    Vect3i getOrigin();

    Axis getAxis();

    double getAngle();

    boolean rescale();

    enum Axis{
        X, Y, Z
    }
}
