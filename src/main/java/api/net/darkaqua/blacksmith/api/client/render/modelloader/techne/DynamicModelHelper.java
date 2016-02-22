package net.darkaqua.blacksmith.api.client.render.modelloader.techne;

import net.darkaqua.blacksmith.api.common.util.Direction;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3i;
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
