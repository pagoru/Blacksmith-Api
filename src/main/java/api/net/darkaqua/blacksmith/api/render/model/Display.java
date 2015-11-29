package net.darkaqua.blacksmith.api.render.model;

import net.darkaqua.blacksmith.api.util.Vector3d;

/**
 * Created by cout970 on 28/11/2015.
 */
public class Display {

    private Vector3d rotation;
    private Vector3d translation;
    private Vector3d scale;

    public Display(Vector3d rotation, Vector3d translation, Vector3d scale) {
        this.rotation = rotation;
        this.translation = translation;
        this.scale = scale;
    }

    public Vector3d getRotation() {
        return rotation;
    }

    public Vector3d getTranslation() {
        return translation;
    }

    public Vector3d getScale() {
        return scale;
    }
}
