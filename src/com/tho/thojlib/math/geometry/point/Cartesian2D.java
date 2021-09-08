package com.tho.thojlib.math.geometry.point;

import com.tho.thojlib.math.vector.Vector;

public class Cartesian2D extends Vector {
	private static final int DIMENSION = 2;

	private static final int X_INDEX = 0;
	private static final int Y_INDEX = 1;

	public Cartesian2D() {
		super(Cartesian2D.DIMENSION);
	}

	public Cartesian2D(final double x, final double y) {
		super(Cartesian2D.DIMENSION);
		set(x, y);
	}

	public void set(final double x, final double y) {
		this.matrix[Cartesian2D.X_INDEX][0] = x;
		this.matrix[Cartesian2D.Y_INDEX][0] = y;
	}

	public void setX(final double x) {
		this.matrix[Cartesian2D.X_INDEX][0] = x;
	}

	public double getX() {
		return this.matrix[Cartesian2D.X_INDEX][0];
	}

	public void setY(final double y) {
		this.matrix[Cartesian2D.Y_INDEX][0] = y;
	}

	public double getY() {
		return this.matrix[Cartesian2D.Y_INDEX][0];
	}

	@Override
	public Cartesian2D clone() {
		final Cartesian2D clone = new Cartesian2D();
		clone.copy(this);
		return clone;
	}
}