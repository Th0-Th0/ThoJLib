package com.tho.thojlib.math.geometry.point;

import com.tho.thojlib.math.geometry.AngleUtils;
import com.tho.thojlib.math.vector.Vector;

public class Polar2D extends Vector {
	private static final int DIMENSION = 2;

	private static final int R_INDEX = 0;
	private static final int THETA_INDEX = 1;

	public Polar2D() {
		super(Polar2D.DIMENSION);
	}

	public Polar2D(final double r, final double theta) {
		super(Polar2D.DIMENSION);
		set(r, theta);
	}

	public void set(final double r, final double theta) {
		setTheta(theta);
		setR(r);
	}

	public void setR(final double r) {
		this.matrix[Polar2D.R_INDEX][0] = Math.abs(r);
		if (r < 0) {
			setTheta(getTheta() + Math.PI);
		}
	}

	public double getR() {
		return this.matrix[Polar2D.R_INDEX][0];
	}

	public void setTheta(final double theta) {
		this.matrix[Polar2D.THETA_INDEX][0] = AngleUtils.foldPi(theta);
	}

	public double getTheta() {
		return this.matrix[Polar2D.THETA_INDEX][0];
	}

	@Override
	public void set(final int row, final int column, final double value) {
		if (row == Polar2D.R_INDEX && column == 0) {
			setR(value);
		} else if (row == Polar2D.THETA_INDEX && column == 0) {
			setTheta(value);
		} else {
			super.set(row, column, value);
		}
	}

	@Override
	public Polar2D clone() {
		final Polar2D clone = new Polar2D();
		clone.copy(this);
		return clone;
	}
}