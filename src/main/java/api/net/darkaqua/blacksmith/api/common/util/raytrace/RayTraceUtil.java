package net.darkaqua.blacksmith.api.common.util.raytrace;

import net.darkaqua.blacksmith.api.common.util.Direction;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3i;

/**
 * Created by cout970 on 22/01/2016.
 */
public class RayTraceUtil {

    public static final double EPSILON = 1.0000000116860974E-7D;

    public static RayTraceResult collisionRayTrace(Vect3i pos, Cube box, Vect3d start, Vect3d end) {

        start = start.copy().add(pos.getOpposite().toVect3d());
        end = end.copy().add(pos.getOpposite().toVect3d());

        Vect3d minX = getIntermediateWithXValue(start, end, box.minX());
        Vect3d maxX = getIntermediateWithXValue(start, end, box.maxX());
        Vect3d minY = getIntermediateWithYValue(start, end, box.minY());
        Vect3d maxY = getIntermediateWithYValue(start, end, box.maxY());
        Vect3d minZ = getIntermediateWithZValue(start, end, box.minZ());
        Vect3d maxZ = getIntermediateWithZValue(start, end, box.maxZ());

        if (!isVecInsideYZBounds(minX, box)) {
            minX = null;
        }

        if (!isVecInsideYZBounds(maxX, box)) {
            maxX = null;
        }

        if (!isVecInsideXZBounds(minY, box)) {
            minY = null;
        }

        if (!isVecInsideXZBounds(maxY, box)) {
            maxY = null;
        }

        if (!isVecInsideXYBounds(minZ, box)) {
            minZ = null;
        }

        if (!isVecInsideXYBounds(maxZ, box)) {
            maxZ = null;
        }

        Vect3d result = null;

        if (minX != null) {
            result = minX;
        }

        if (maxX != null && (result == null || start.distanceSquared(maxX) < start.distanceSquared(result))) {
            result = maxX;
        }

        if (minY != null && (result == null || start.distanceSquared(minY) < start.distanceSquared(result))) {
            result = minY;
        }

        if (maxY != null && (result == null || start.distanceSquared(maxY) < start.distanceSquared(result))) {
            result = maxY;
        }

        if (minZ != null && (result == null || start.distanceSquared(minZ) < start.distanceSquared(result))) {
            result = minZ;
        }

        if (maxZ != null && (result == null || start.distanceSquared(maxZ) < start.distanceSquared(result))) {
            result = maxZ;
        }

        if (result == null) {
            return null;
        } else {
            Direction side = null;

            if (result == minX) {
                side = Direction.WEST;
            }

            if (result == maxX) {
                side = Direction.EAST;
            }

            if (result == minY) {
                side = Direction.DOWN;
            }

            if (result == maxY) {
                side = Direction.UP;
            }

            if (result == minZ) {
                side = Direction.NORTH;
            }

            if (result == maxZ) {
                side = Direction.SOUTH;
            }

            return new RayTraceResult(result.add(pos.toVect3d()), side, pos);
        }
    }

    public static boolean isVecInsideYZBounds(Vect3d point, Cube box) {
        return point != null && (point.getY() >= box.minY() && point.getY() <= box.maxY() && point.getZ() >= box.minZ() && point.getZ() <= box.maxZ());
    }

    public static boolean isVecInsideXZBounds(Vect3d point, Cube box) {
        return point != null && (point.getX() >= box.minX() && point.getX() <= box.maxX() && point.getZ() >= box.minZ() && point.getZ() <= box.maxZ());
    }

    public static boolean isVecInsideXYBounds(Vect3d point, Cube box) {
        return point != null && (point.getX() >= box.minX() && point.getX() <= box.maxX() && point.getY() >= box.minY() && point.getY() <= box.maxY());
    }


    public static Vect3d getIntermediateWithXValue(Vect3d start, Vect3d end, double x) {

        Vect3d diff = end.copy().sub(start);

        if (diff.getX() * diff.getX() < EPSILON) {
            return null;
        } else {
            double d3 = (x - start.getX()) / diff.getX();
            return d3 >= 0.0D && d3 <= 1.0D ? start.copy().add(diff.multiply(d3)) : null;
        }
    }

    public static Vect3d getIntermediateWithYValue(Vect3d start, Vect3d end, double y) {

        Vect3d diff = end.copy().sub(start);

        if (diff.getY() * diff.getY() < EPSILON) {
            return null;
        } else {
            double d3 = (y - start.getY()) / diff.getY();
            return d3 >= 0.0D && d3 <= 1.0D ? start.copy().add(diff.multiply(d3)) : null;
        }
    }

    public static Vect3d getIntermediateWithZValue(Vect3d start, Vect3d end, double z) {
        Vect3d diff = end.copy().sub(start);

        if (diff.getZ() * diff.getZ() < EPSILON) {
            return null;
        } else {
            double d3 = (z - start.getZ()) / diff.getZ();
            return d3 >= 0.0D && d3 <= 1.0D ? start.copy().add(diff.multiply(d3)) : null;
        }
    }
}
