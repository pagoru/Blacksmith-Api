package net.darkaqua.blacksmith.mod.temp.old_json;

import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.Vector4d;

/**
 * Created by cout970 on 28/11/2015.
 */
public interface IJsonModelFace {

    Vector4d getUV();

    String getTextureID();

    Direction getCullFace();

    int getTextureRotation();
}