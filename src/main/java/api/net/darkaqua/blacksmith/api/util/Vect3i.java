package net.darkaqua.blacksmith.api.util;

/**
 * @author cout970
 */
public class Vect3i {

    protected int x;
    protected int y;
    protected int z;

    public Vect3i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vect3i(double x, double y, double z) {
        this((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z));
    }

    public Vect3i(int[] ar) {
        this(ar[0], ar[1], ar[2]);
    }

    public static Vect3i nullVector() {
        return new Vect3i(0, 0, 0);
    }

    public Vect3i getOpposite() {
        return new Vect3i(-x, -y, -z);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof Vect3i)) {
            return false;
        } else {
            Vect3i vecInt = (Vect3i) obj;
            return this.getX() == vecInt.getX() && (this.getY() == vecInt.getY() && this.getZ() == vecInt.getZ());
        }
    }

    @Override
    public int hashCode() {
        return (this.getY() + this.getZ() * 31) * 31 + this.getX();
    }

    public int compareTo(Vect3i vec) {
        return this.getY() == vec.getY() ? (this.getZ() == vec.getZ() ? this.getX() - vec.getX() : this
                .getZ() - vec.getZ()) : this.getY() - vec.getY();
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }

    @Override
    public String toString() {
        return "Vect3i: x: " + getX() + ", y: " + getY() + ", z: " + getZ();
    }

    public Vect3i multiply(int i) {
        x *= i;
        y *= i;
        z *= i;
        return this;
    }

    public Vect3i add(Vect3i v) {
        x += v.x;
        y += v.y;
        z += v.z;
        return this;
    }

    public Vect3i add(int a, int b, int c) {
        x += a;
        y += b;
        z += c;
        return this;
    }

    public Vect3i add(Direction dir) {
        return add(dir.getOffsetX(), dir.getOffsetY(), dir.getOffsetZ());
    }

    public Vect3i copy() {
        return new Vect3i(x, y, z);
    }

    public int[] intArray() {
        return new int[]{x, y, z};
    }

    public int magnitude() {
        return x * x + y * y + z * z;
    }

    public Vect3d toVector3d() {
        return new Vect3d(getX(), getY(), getZ());
    }

    public Vect3i up(int times) {
        return add(Direction.UP.toVect3i().multiply(times));
    }

    public Vect3i down(int times) {
        return add(Direction.DOWN.toVect3i().multiply(times));
    }

    public Vect3i south(int times) {
        return add(Direction.SOUTH.toVect3i().multiply(times));
    }

    public Vect3i north(int times) {
        return add(Direction.NORTH.toVect3i().multiply(times));
    }

    public Vect3i west(int times) {
        return add(Direction.WEST.toVect3i().multiply(times));
    }

    public Vect3i east(int times) {
        return add(Direction.EAST.toVect3i().multiply(times));
    }

    public Vect3i up() {
        return add(Direction.UP);
    }

    public Vect3i down() {
        return add(Direction.DOWN);
    }

    public Vect3i south() {
        return add(Direction.SOUTH);
    }

    public Vect3i north() {
        return add(Direction.NORTH);
    }

    public Vect3i west() {
        return add(Direction.WEST);
    }

    public Vect3i east() {
        return add(Direction.EAST);
    }

}
