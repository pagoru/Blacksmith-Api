package net.darkaqua.blacksmith.api.util;

/**
 * Created by cout970 on 15/12/2015.
 */
public class Vect3d {

    protected double x;
    protected double y;
    protected double z;

    public Vect3d(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vect3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vect3d(Vect3d vec) {
        this(vec.getX(), vec.getY(), vec.getZ());
    }

    public static Vect3d nullVector(){
        return new Vect3d(0,0,0);
    }

    public Vect3d(int[] ar) {
        this(ar[0], ar[1], ar[2]);
    }

    public Vect3d(float[] ar) {
        this(ar[0], ar[1], ar[2]);
    }

    public Vect3d(double[] ar) {
        this(ar[0], ar[1], ar[2]);
    }

    public Vect3d getOpposite() {
        return new Vect3d(-x, -y, -z);
    }

    public Vect3d set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return String.format("Vect3d: x: %.3f, y: %.3f, z: %.3f", getX(), getY(), getZ());
    }

    public Vect3d multiply(double i) {
        x *= i;
        y *= i;
        z *= i;
        return this;
    }

    public Vect3d add(Vect3d v) {
        x += v.x;
        y += v.y;
        z += v.z;
        return this;
    }

    public Vect3d sub(Vect3d v) {
        x -= v.x;
        y -= v.y;
        z -= v.z;
        return this;
    }

    public Vect3d add(Direction dir) {
        return add(dir.getOffsetX(), dir.getOffsetY(), dir.getOffsetZ());
    }

    public Vect3d add(double a, double b, double c) {
        x += a;
        y += b;
        z += c;
        return this;
    }

    public Vect3d substract(double a, double b, double c) {
        x -= a;
        y -= b;
        z -= c;
        return this;
    }

    public Vect3d copy() {
        return new Vect3d(x, y, z);
    }

    /**
     * Returns a array of doubles with the components of the vector
     */
    public double[] doubleArray() {
        return new double[] { x, y, z };
    }

    /**
     * Returns the magnitude squared of the vector
     */
    public double magSquared() {
        return x * x + y * y + z * z;
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
    public double distance(Vect3d vector) {
        Vect3d line = vector.copy().add(getOpposite());
        return Math.sqrt(line.magSquared());
    }

    public Vect3i toVector3i() {
        return new Vect3i(getX(), getY(), getZ());
    }

    public double dotProduct(Vect3d vec) {
        return vec.x * x + vec.y * y + vec.z * z;
    }

    public Vect3d crossProduct(Vect3d vec) {
        return new Vect3d((y * vec.z) - (z * vec.y), (z * vec.x) - (x * vec.z), (x * vec.y) - (y * vec.x));
    }

    /**
     * Creates a new Vect3d with magnitude equals 0
     */
    public Vect3d unitVector() {
        if (isNullVector())
            return nullVector();
        return this.copy().multiply(1 / mag());
    }

    public Vect3d normalize() {
        double mag = mag();
        if (mag != 0) {
            this.multiply(1 / mag);
        }
        return this;
    }

    public boolean isNullVector() {
        return x == 0 && y == 0 && z == 0;
    }

    public double angle(Vect3d vec) {
        if (mag() * vec.mag() == 0)
            return 0;
        return Math.acos(copy().dotProduct(vec) / (mag() * vec.mag()));
    }

    public Vect3d rotateX(double angle) {
        Vect3d[] rotationMatrix = { new Vect3d(1, 0, 0), new Vect3d(0, Math.cos(angle),
                -Math.sin(angle)), new Vect3d(0, Math.sin(angle), Math.cos(angle)) };

        double i, j, k;
        i = dotProduct(rotationMatrix[0]);
        j = dotProduct(rotationMatrix[1]);
        k = dotProduct(rotationMatrix[2]);
        set(i, j, k);
        return this;
    }

    public Vect3d rotateY(double angle) {
        Vect3d[] rotationMatrix = { new Vect3d(Math.cos(angle), 0, Math.sin(angle)), new Vect3d(0, 1,
                0), new Vect3d(-Math.sin(angle), 0, Math.cos(angle)) };

        double i, j, k;
        i = dotProduct(rotationMatrix[0]);
        j = dotProduct(rotationMatrix[1]);
        k = dotProduct(rotationMatrix[2]);
        set(i, j, k);
        return this;
    }

    public Vect3d rotateZ(double angle) {
        Vect3d[] rotationMatrix = { new Vect3d(Math.cos(angle), -Math.sin(angle),
                0), new Vect3d(Math.sin(angle), Math.cos(angle), 0), new Vect3d(0, 0, 1) };

        double i, j, k;
        i = dotProduct(rotationMatrix[0]);
        j = dotProduct(rotationMatrix[1]);
        k = dotProduct(rotationMatrix[2]);
        set(i, j, k);
        return this;
    }

    public Vect3d rotateCustom(Vect3d axis, double angle) {
        if (axis.isNullVector())
            return this;

        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        Vect3d[] rotationMatrix = { new Vect3d(cos + axis.x * axis.x * (1 - cos),
                axis.y * axis.x * (1 - cos) + axis.z * sin,
                axis.z * axis.x * (1 - cos) - axis.y * sin), new Vect3d(
                axis.x * axis.y * (1 - cos) - axis.z * sin, cos + axis.y * axis.y * (1 - cos),
                axis.z * axis.y * (1 - cos) + axis.z * sin), new Vect3d(
                axis.x * axis.z * (1 - cos) + axis.y * sin, axis.y * axis.z * (1 - cos),
                cos + axis.z * axis.z * (1 - cos)) };

        double i, j, k;
        i = dotProduct(rotationMatrix[0]);
        j = dotProduct(rotationMatrix[1]);
        k = dotProduct(rotationMatrix[2]);
        set(i, j, k);
        return this;
    }
}
