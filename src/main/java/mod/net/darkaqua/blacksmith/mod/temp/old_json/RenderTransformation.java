package net.darkaqua.blacksmith.mod.temp.old_json;


import javax.vecmath.Vector3d;
import javax.vecmath.Vector4d;

/**
 * Created by cout970 on 28/11/2015.
 */
public class RenderTransformation {

    private Vector4d rotation;
    private Vector3d translation;
    private Vector3d scale;

    public RenderTransformation(Vector3d translation, Vector4d rotation, Vector3d scale) {
        this.rotation = rotation;
        this.translation = translation;
        this.scale = scale;
    }

    public Vector4d getRotation() {
        return rotation;
    }

    public Vector3d getTranslation() {
        return translation;
    }

    public Vector3d getScale() {
        return scale;
    }
}
