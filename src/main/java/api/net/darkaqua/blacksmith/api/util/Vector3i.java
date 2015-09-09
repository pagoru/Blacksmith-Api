package net.darkaqua.blacksmith.api.util;

public class Vector3i {

	public static final Vector3i NULL_VECTOR = new Vector3i(0, 0, 0);
	protected int x;
	protected int y;
	protected int z;

	public Vector3i(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3i(double x, double y, double z) {
		this((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z));
	}

	public Vector3i(int[] ar) {
		this(ar[0], ar[1], ar[2]);
	}

	public Vector3i getOpposite() {
		return new Vector3i(-x, -y, -z);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (!(obj instanceof Vector3i)) {
			return false;
		} else {
			Vector3i vecInt = (Vector3i) obj;
			return this.getX() != vecInt
					.getX() ? false : (this.getY() != vecInt.getY() ? false : this.getZ() == vecInt.getZ());
		}
	}

	@Override
	public int hashCode() {
		return (this.getY() + this.getZ() * 31) * 31 + this.getX();
	}

	public int compareTo(Vector3i vec) {
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
		return "x: " + getX() + ", y: " + getY() + ", z: " + getZ();
	}

	public Vector3i multiply(int i) {
		x *= i;
		y *= i;
		z *= i;
		return this;
	}

	public Vector3i add(Vector3i v) {
		x += v.x;
		y += v.y;
		z += v.z;
		return this;
	}

	public Vector3i add(int a, int b, int c) {
		x += a;
		y += b;
		z += c;
		return this;
	}

	public Vector3i add(Direction dir) {
		return add(dir.getOffsetX(), dir.getOffsetY(), dir.getOffsetZ());
	}

	public Vector3i copy() {
		return new Vector3i(x, y, z);
	}

	public int[] intArray() {
		return new int[] { x, y, z };
	}

	public int magnitude() {
		return x * x + y * y + z * z;
	}

	public Vector3d toVector3d() {
		return new Vector3d(getX(), getY(), getZ());
	}
}
