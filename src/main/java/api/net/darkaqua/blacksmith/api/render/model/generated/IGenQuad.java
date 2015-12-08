package net.darkaqua.blacksmith.api.render.model.generated;

import net.darkaqua.blacksmith.api.render.ResourceReference;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.Vector2d;
import net.darkaqua.blacksmith.api.util.Vector3d;

/**
 * Created by cout970 on 07/12/2015.
 */
public interface IGenQuad {

    Vector3d getVertex(QuadVertex pos);

    Vector2d getUV(QuadVertex pos);

    Direction getNormal();

    ResourceReference getTexture();

    enum QuadVertex{
        FIRST,
        SECOND,
        THIRD,
        FOURTH
    }
}
