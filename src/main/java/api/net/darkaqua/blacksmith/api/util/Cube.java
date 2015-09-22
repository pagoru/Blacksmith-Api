package net.darkaqua.blacksmith.api.util;

public class Cube {

	protected Vector3d start;
	protected Vector3d end;

	public Cube(double x0, double y0, double z0, double x1, double y1, double z1) {
		start = new Vector3d(x0, y0, z0);
		end = new Vector3d(x1, y1, z1);
	}

	public Cube(Vector3d start, Vector3d end) {
		this.start = start.copy();
		this.end = end.copy();
	}

	public static Cube fullBlock() {
		return new Cube(0, 0, 0, 1, 1, 1);
	}

	public static Cube infinite() {
		return new Cube(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY,
				Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
	}

	public Cube copy() {
		return new Cube(start, end);
	}

	public Vector3d min() {
		return new Vector3d(Math.min(start.getX(), end.getX()), Math.min(start.getY(), end.getY()),
				Math.min(start.getZ(), end.getZ()));
	}

	public Vector3d max() {
		return new Vector3d(Math.max(start.getX(), end.getX()), Math.max(start.getY(), end.getY()),
				Math.max(start.getZ(), end.getZ()));
	}
}
