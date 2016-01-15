package net.darkaqua.blacksmith.api.util;

/**
 * Created by cout970 on 15/12/2015.
 */
public class Vect2i {

    protected int x;
    protected int y;

    public Vect2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vect2i(double x, double y) {
        this.x = (int) x;
        this.y = (int) y;
    }

    public Vect2i(Vect2i vec) {
        this(vec.getX(), vec.getY());
    }

    public static Vect2i nullVector() {
        return new Vect2i(0, 0);
    }

    public Vect2i(int[] ar) {
        this(ar[0], ar[1]);
    }

    public Vect2i(float[] ar) {
        this(ar[0], ar[1]);
    }

    public Vect2i(double[] ar) {
        this(ar[0], ar[1]);
    }

    public Vect2i getOpposite() {
        return new Vect2i(-x, -y);
    }

    public Vect2d toVect2d() {
        return new Vect2d(getX(), getY());
    }

    public Vect2i set(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return String.format("Vect2d: x: %d, y: %d", getX(), getY());
    }

    public Vect2i multiply(int i) {
        x *= i;
        y *= i;
        return this;
    }

    public Vect2i add(Vect2i v) {
        x += v.x;
        y += v.y;
        return this;
    }

    public Vect2i sub(Vect2i v) {
        x -= v.x;
        y -= v.y;
        return this;
    }


    public Vect2i add(int a, int b) {
        x += a;
        y += b;
        return this;
    }

    public Vect2i sub(int a, int b) {
        x -= a;
        y -= b;
        return this;
    }

    public Vect2i copy() {
        return new Vect2i(x, y);
    }

    /**
     * Returns a array of doubles with the components of the vector
     */
    public int[] intArray() {
        return new int[]{x, y};
    }

    /**
     * Returns the magnitude squared of the vector
     */
    public int magSquared() {
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
    public double distance(Vect2i vector) {
        Vect2i line = vector.copy().add(getOpposite());
        return Math.sqrt(line.magSquared());
    }

    public int dotProduct(Vect2i vec) {
        return vec.x * x + vec.y * y;
    }

    public boolean isNullVector() {
        return x == 0 && y == 0;
    }

    public double angle(Vect2i vec) {
        if (mag() * vec.mag() == 0)
            return 0;
        return Math.acos(copy().dotProduct(vec) / (mag() * vec.mag()));
    }
}
