package net.darkaqua.blacksmith.api.client.render.model;

import net.darkaqua.blacksmith.api.common.util.Direction;
import net.darkaqua.blacksmith.api.common.util.ResourceReference;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect2d;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;
import net.darkaqua.blacksmith.api.common.util.annotations.Implementable;

/**
 * Created by cout970 on 07/12/2015.
 */
@Implementable
public interface IModelQuad {

    Vect3d getVertex(QuadVertex pos);

    Vect2d getUV(QuadVertex pos);

    /**
     * This is not the normal vector of the texture, this is the side that minecraft will use to exclude the quad to render
     * if there are other block covering the side
     * @return the side, can be null
     */
    Direction getSide();

    ResourceReference getTexture();

    boolean useShade();

    enum QuadVertex {
        FIRST,
        SECOND,
        THIRD,
        FOURTH
    }
}
