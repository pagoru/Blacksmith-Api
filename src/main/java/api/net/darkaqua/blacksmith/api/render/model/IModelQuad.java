package net.darkaqua.blacksmith.api.render.model;

import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect2d;
import net.darkaqua.blacksmith.api.util.Vect3d;

/**
 * Created by cout970 on 07/12/2015.
 */
public interface IModelQuad {

    Vect3d getVertex(QuadVertex pos);

    Vect2d getUV(QuadVertex pos);

    Direction getNormal();

    ResourceReference getTexture();

    boolean useShade();

    enum QuadVertex {
        FIRST,
        SECOND,
        THIRD,
        FOURTH
    }
}
