package net.darkaqua.blacksmith.api.render.model.json;

import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.Vector3i;

/**
 * Created by cout970 on 28/11/2015.
 */
public interface IModelElement {

    Vector3i getStartPoint();
    Vector3i getEndPoint();

    IModelRotation getRotation();

    boolean shouldRenderShadows();

    IModelFace getFace(Direction dir);
}
