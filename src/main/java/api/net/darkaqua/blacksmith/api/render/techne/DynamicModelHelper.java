package net.darkaqua.blacksmith.api.render.techne;

import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.api.util.Vect3i;
import org.lwjgl.opengl.GL11;

/**
 * Created by cout970 on 31/01/2016.
 */
public class DynamicModelHelper {

    public static void rotate(float angle, Direction.Axis axis, Vect3d rotationPoint){
        GL11.glTranslated(rotationPoint.getX(),rotationPoint.getY(),rotationPoint.getZ());
        Vect3i dir = axis.getPositiveDir().toVect3i();
        GL11.glRotatef(angle, dir.getX(), dir.getY(), dir.getZ());
        GL11.glTranslated(-rotationPoint.getX(),-rotationPoint.getY(),-rotationPoint.getZ());
    }
}
