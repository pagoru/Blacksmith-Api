package net.darkaqua.blacksmith.mod.temp.old_json;

import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.Vector3i;

/**
 * Created by cout970 on 28/11/2015.
 */
public interface IJsonModelElement {

    Vector3i getStartPoint();
    Vector3i getEndPoint();

    IJsonModelRotation getRotation();

    boolean shouldRenderShadows();

    IJsonModelFace getFace(Direction dir);
}
