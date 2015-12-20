package net.darkaqua.blacksmith.api.util;

/**
 * Created by cout970 on 15/12/2015.
 */
public class Vect2d {

    protected double x;
    protected double y;

    public Vect2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vect2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vect2d(Vect2d vec) {
        this(vec.getX(), vec.getY());
    }

    public static Vect2d nullVector(){
        return new Vect2d(0,0);
    }

    public Vect2d(int[] ar) {
        this(ar[0], ar[1]);
    }

    public Vect2d(float[] ar) {
        this(ar[0], ar[1]);
    }

    public Vect2d(double[] ar) {
        this(ar[0], ar[1]);
    }

    public Vect2d getOpposite() {
        return new Vect2d(-x, -y);
    }

    public Vect2i toVect2i(){
        return new Vect2i(getX(), getY());
    }

    public Vect2d set(double x, double y) {
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
        return String.format("Vect2d: x: %.3f, y: %.3f", getX(), getY());
    }

    public Vect2d multiply(double i) {
        x *= i;
        y *= i;
        return this;
    }

    public Vect2d add(Vect2d v) {
        x += v.x;
        y += v.y;
        return this;
    }

    public Vect2d sub(Vect2d v) {
        x -= v.x;
        y -= v.y;
        return this;
    }


    public Vect2d add(double a, double b) {
        x += a;
        y += b;
        return this;
    }

    public Vect2d sub(double a, double b) {
        x -= a;
        y -= b;
        return this;
    }

    public Vect2d copy() {
        return new Vect2d(x, y);
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
    public double distance(Vect2d vector) {
        Vect2d line = vector.copy().add(getOpposite());
        return Math.sqrt(line.magSquared());
    }

    public double dotProduct(Vect2d vec) {
        return vec.x * x + vec.y * y;
    }

    /**
     * Creates a new vector3d with magnitude equals 0
     */
    public Vect2d unitVector() {
        if (isNullVector())
            return nullVector();
        return this.copy().multiply(1 / mag());
    }

    public Vect2d normalize() {
        double mag = mag();
        if (mag != 0) {
            this.multiply(1 / mag);
        }
        return this;
    }

    public boolean isNullVector() {
        return x == 0 && y == 0;
    }

    public double angle(Vect2d vec) {
        if (mag() * vec.mag() == 0)
            return 0;
        return Math.acos(copy().dotProduct(vec) / (mag() * vec.mag()));
    }
}
