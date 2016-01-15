package net.darkaqua.blacksmith.api.util;

public enum Direction {

    DOWN(0, -1, 0, Axis.Y),
    UP(0, 1, 0, Axis.Y),
    NORTH(0, 0, -1, Axis.Z),
    SOUTH(0, 0, 1, Axis.Z),
    WEST(-1, 0, 0, Axis.X),
    EAST(1, 0, 0, Axis.X);

    public static final Direction[] VALID_DIRECTIONS = {DOWN, UP, NORTH, SOUTH, WEST, EAST};
    public static final Direction[] OPPOSITES = {UP, DOWN, SOUTH, NORTH, EAST, WEST};
    public static final int[][] rotation = {
            {0, 1, 5, 4, 2, 3},
            {0, 1, 4, 5, 3, 2},
            {5, 4, 2, 3, 0, 1},
            {4, 5, 2, 3, 1, 0},
            {2, 3, 1, 0, 4, 5},
            {3, 2, 0, 1, 4, 5},
            {0, 1, 2, 3, 4, 5}};

    private final Vect3i offsets;
    private final Axis axis;

    Direction(int x, int y, int z, Axis a) {
        offsets = new Vect3i(x, y, z);
        axis = a;
    }

    public int getOffsetX() {
        return offsets.getX();
    }

    public int getOffsetY() {
        return offsets.getY();
    }

    public int getOffsetZ() {
        return offsets.getZ();
    }

    public Axis getAxis() {
        return axis;
    }

    public Direction opposite() {
        return OPPOSITES[ordinal()];
    }

    public static Direction getDirection(int i) {
        return values()[i % VALID_DIRECTIONS.length];
    }

    public Vect3i toVect3i() {
        return offsets.copy();
    }

    //anti-clockwise
    public Direction step(Direction axis) {
        return Direction.getDirection(rotation[axis.ordinal()][ordinal()]);
    }

    public boolean isPerpendicular(Direction dir) {
        return Math.abs(dir.getOffsetX()) != Math.abs(getOffsetX()) || Math.abs(dir.getOffsetY()) != Math.abs(getOffsetY()) || Math.abs(dir.getOffsetZ()) != Math.abs(getOffsetZ());
    }

    public boolean isParallel(Direction dir) {
        return !isPerpendicular(dir);
    }

    public boolean matches(Vect3i offset) {
        return offsets.equals(offset);
    }

    public enum Axis {
        X,
        Y,
        Z
    }
}
