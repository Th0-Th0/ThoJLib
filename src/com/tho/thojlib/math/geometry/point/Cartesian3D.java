package com.tho.thojlib.math.geometry.point;

import com.tho.thojlib.math.vector.Vector;

public class Cartesian3D extends Vector {
	private static final int DIMENSION = 3;

	private static final int X_INDEX = 0;
	private static final int Y_INDEX = 1;
	private static final int Z_INDEX = 2;

	public Cartesian3D() {
		super(Cartesian3D.DIMENSION);
	}

	public Cartesian3D(final double x, final double y, final double z) {
		super(Cartesian3D.DIMENSION);
		set(x, y, z);
	}

	public void set(final double x, final double y, final double z) {
		this.matrix[Cartesian3D.X_INDEX][0] = x;
		this.matrix[Cartesian3D.Y_INDEX][0] = y;
		this.matrix[Cartesian3D.Z_INDEX][0] = z;
	}

	public void setX(final double x) {
		this.matrix[Cartesian3D.X_INDEX][0] = x;
	}

	public double getX() {
		return this.matrix[Cartesian3D.X_INDEX][0];
	}

	public void setY(final double y) {
		this.matrix[Cartesian3D.Y_INDEX][0] = y;
	}

	public double getY() {
		return this.matrix[Cartesian3D.Y_INDEX][0];
	}

	public void setZ(final double z) {
		this.matrix[Cartesian3D.Z_INDEX][0] = z;
	}

	public double getZ() {
		return this.matrix[Cartesian3D.Z_INDEX][0];
	}

	@Override
	public Cartesian3D clone() {
		final Cartesian3D clone = new Cartesian3D();
		clone.copy(this);
		return clone;
	}
}