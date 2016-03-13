package net.darkaqua.blacksmith.api.client.render.model;

import net.darkaqua.blacksmith.api.common.util.Direction;
import net.darkaqua.blacksmith.api.common.util.annotations.Implementable;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect2d;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;

/**
 * Created by cout970 on 07/12/2015.
 */
@Implementable
public interface IModelQuad {

    Vect3d getVertex(QuadVertex pos);

    Vect2d getUV(QuadVertex pos);

    /**
     * This is not the normal vector of the texture, this is the sides that minecraft will use to exclude the quads to render
     * if there are other block covering the sides
     * @return the sides, can be null
     */
    Direction getSide();

    enum QuadVertex {
        FIRST,
        SECOND,
        THIRD,
        FOURTH
    }
}
