package net.darkaqua.blacksmith.api.util;

/**
 * Created by cout970 on 07/12/2015.
 */
public class Vector2d {

    protected double x;
    protected double y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2d nullVector(){
        return new Vector2d(0,0);
    }

    public Vector2d(int[] ar) {
        this(ar[0], ar[1]);
    }

    public Vector2d(float[] ar) {
        this(ar[0], ar[1]);
    }

    public Vector2d(double[] ar) {
        this(ar[0], ar[1]);
    }

    public Vector2d getOpposite() {
        return new Vector2d(-x, -y);
    }

    public Vector2d set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return String.format("Vector2d: x: %.3f, y: %.3f", getX(), getY());
    }

    public Vector2d multiply(double i) {
        x *= i;
        y *= i;
        return this;
    }

    public Vector2d add(Vector2d v) {
        x += v.x;
        y += v.y;
        return this;
    }

    public Vector2d substract(Vector2d v) {
        x -= v.x;
        y -= v.y;
        return this;
    }


    public Vector2d add(double a, double b) {
        x += a;
        y += b;
        return this;
    }

    public Vector2d substract(double a, double b) {
        x -= a;
        y -= b;
        return this;
    }

    public Vector2d copy() {
        return new Vector2d(x, y);
    }

    /**
     * Returns a array of doubles with the components of the vector
     */
    public double[] doubleArray() {
        return new double[] { x, y};
    }

    /**
     * Returns the magnitude squared of the vector
     */
    public double magSquared() {
        return x * x + y * y;
    }

    /**
     * Returns the magnitude of the vector
     */
    public double mag() {
        return Math.sqrt(magSquared());
    }

    /**
     * Returns the distance from this point to the point specified by the first argument
     */
    public double distance(Vector2d vector) {
        Vector2d line = vector.copy().add(getOpposite());
        return Math.sqrt(line.magSquared());
    }

    public double dotProduct(Vector2d vec) {
        return vec.x * x + vec.y * y;
    }

    /**
     * Creates a new vector3d with magnitude equals 0
     */
    public Vector2d unitVector() {
        if (isNullVector())
            return nullVector();
        return this.copy().multiply(1 / mag());
    }

    public Vector2d normalize() {
        double mag = mag();
        if (mag != 0) {
            this.multiply(1 / mag);
        }
        return this;
    }

    public boolean isNullVector() {
        return x == 0 && y == 0;
    }

    public double angle(Vector2d vec) {
        if (mag() * vec.mag() == 0)
            return 0;
        return Math.acos(copy().dotProduct(vec) / (mag() * vec.mag()));
    }
}
