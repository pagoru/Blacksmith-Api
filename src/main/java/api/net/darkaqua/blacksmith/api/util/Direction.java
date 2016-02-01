package net.darkaqua.blacksmith.api.util;

public enum Direction {

    DOWN(0, -1, 0, Axis.Y, AxisDirection.NEGATIVE),
    UP(0, 1, 0, Axis.Y, AxisDirection.POSITIVE),
    NORTH(0, 0, -1, Axis.Z, AxisDirection.NEGATIVE),
    SOUTH(0, 0, 1, Axis.Z, AxisDirection.POSITIVE),
    WEST(-1, 0, 0, Axis.X, AxisDirection.NEGATIVE),
    EAST(1, 0, 0, Axis.X, AxisDirection.POSITIVE);

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
    private final AxisDirection axisDir;

    Direction(int x, int y, int z, Axis axis, AxisDirection axisDir) {
        offsets = new Vect3i(x, y, z);
        this.axis = axis;
        this.axisDir = axisDir;
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

    public Direction rotate(Axis axis, boolean clockwise) {
        return step(clockwise ? axis.getNegativeDir() : axis.getPositiveDir());
    }

    public boolean isPerpendicular(Direction dir) {
        return !isParallel(dir);
    }

    public boolean isParallel(Direction dir) {
        return dir.getAxis() == getAxis();
    }

    public boolean matches(Vect3i offset) {
        return offsets.equals(offset);
    }

    public AxisDirection getAxisDirection(){
        return axisDir;
    }

    public enum Axis {
        X(4),
        Y(0),
        Z(2);

        private int negative;

        Axis(int neg){
            this.negative = neg;
        }

        public Direction getPositiveDir(){
            return getDirection(negative).opposite();
        }

        public Direction getNegativeDir(){
            return getDirection(negative);
        }

        public Direction getDirectionByAxisDirection(AxisDirection a){
            return a == AxisDirection.POSITIVE ? getPositiveDir() : getNegativeDir();
        }
    }

    public enum AxisDirection {
        POSITIVE(1),
        NEGATIVE(-1);

        private int direction;

        AxisDirection(int direction){
            this.direction = direction;
        }

        public Direction getDirectionByAxis(Axis a){
            return this == POSITIVE ? a.getPositiveDir() : a.getNegativeDir();
        }
    }
}
