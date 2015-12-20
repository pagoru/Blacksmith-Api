package net.darkaqua.blacksmith.api.render.model;

import net.darkaqua.blacksmith.api.util.Vect3d;

/**
 * Created by cout970 on 19/12/2015.
 */
public class RenderTransformation {

    private Vect3d translation;
    private Vect3d rotation;
    private Vect3d scale;

    public RenderTransformation(Vect3d translation, Vect3d rotation, Vect3d scale) {
        this.translation = translation;
        this.rotation = rotation;
        this.scale = scale;
    }

    public RenderTransformation() {
        this(Vect3d.nullVector(), Vect3d.nullVector(), new Vect3d(1, 1, 1));
    }

    public Vect3d getTranslation() {
        return translation;
    }

    public void setTranslation(Vect3d translation) {
        this.translation = translation;
    }

    public Vect3d getRotation() {
        return rotation;
    }

    public void setRotation(Vect3d rotation) {
        this.rotation = rotation;
    }

    public Vect3d getScale() {
        return scale;
    }

    public void setScale(Vect3d scale) {
        this.scale = scale;
    }
}
