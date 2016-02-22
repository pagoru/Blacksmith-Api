package net.darkaqua.blacksmith.api.util;

import net.darkaqua.blacksmith.api.storage.DataElementFactory;
import net.darkaqua.blacksmith.api.storage.IDataCompound;

import java.io.Serializable;

/**
 * @author cout970
 */
public class Vect3i implements Comparable<Vect3i>, Cloneable, Serializable {

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

    public Vect3i(IDataCompound pos) {
        this(pos.getInteger("x"), pos.getInteger("y"), pos.getInteger("z"));
    }

    public static Vect3i nullVector() {
        return new Vect3i(0, 0, 0);
    }

    public Vect3i getOpposite() {
        return new Vect3i(-x, -y, -z);
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
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

    public Vect3i sub(Vect3i v) {
        return add(v.getOpposite());
    }

    public Vect3i add(int a, int b, int c) {
        x += a;
        y += b;
        z += c;
        return this;
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

    public Vect3d toVect3d() {
        return new Vect3d(getX(), getY(), getZ());
    }

    public boolean isDirectionalOffset() {
        return ((Math.abs(x) + Math.abs(y) + Math.abs(z)) == 1) && ((Math.abs(x) == 1) || (Math.abs(y) == 1) || (Math.abs(z) == 1));
    }

    public Vect3i add(Direction dir) {
        return add(dir.getOffsetX(), dir.getOffsetY(), dir.getOffsetZ());
    }

    public Vect3i move(Direction dir, int times) {
        return add(dir.toVect3i().multiply(times));
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

    public IDataCompound save() {
        IDataCompound list = DataElementFactory.createDataCompound();
        list.setInteger("x", x);
        list.setInteger("y", y);
        list.setInteger("z", z);
        return list;
    }

    public Direction toDirection() {
        for (Direction d : Direction.values()) {
            if (d.matches(this))
                return d;
        }
        return null;
    }

    @Override
    public int compareTo(Vect3i vec) {
        if (vec == null) return -1;
        return this.getY() == vec.getY() ? (this.getZ() == vec.getZ() ? this.getX() - vec.getX() : this
                .getZ() - vec.getZ()) : this.getY() - vec.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vect3i)) return false;

        Vect3i vect3i = (Vect3i) o;

        if (x != vect3i.x) return false;
        if (y != vect3i.y) return false;
        return z == vect3i.z;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + z;
        return result;
    }

    @Override
    public Vect3i clone(){
        return copy();
    }
}
