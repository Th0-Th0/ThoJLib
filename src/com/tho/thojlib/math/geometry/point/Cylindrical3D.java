package com.tho.thojlib.math.geometry.point;

import com.tho.thojlib.math.geometry.AngleUtils;
import com.tho.thojlib.math.vector.Vector;

public class Cylindrical3D extends Vector {
	private static final int DIMENSION = 3;

	private static final int R_INDEX = 0;
	private static final int THETA_INDEX = 1;
	private static final int Z_INDEX = 2;

	public Cylindrical3D() {
		super(Cylindrical3D.DIMENSION);
	}

	public Cylindrical3D(final double r, final double theta, final double z) {
		super(Cylindrical3D.DIMENSION);
		set(r, theta, z);
	}

	public void set(final double r, final double theta, final double z) {
		setTheta(theta);
		setR(r);
		this.matrix[Cylindrical3D.Z_INDEX][0] = z;
	}

	public void setR(final double r) {
		this.matrix[Cylindrical3D.R_INDEX][0] = Math.abs(r);
		if (r < 0) {
			setTheta(getTheta() + Math.PI);
		}
	}

	public double getR() {
		return this.matrix[Cylindrical3D.R_INDEX][0];
	}

	public void setTheta(final double theta) {
		this.matrix[Cylindrical3D.THETA_INDEX][0] = AngleUtils.foldPi(theta);
	}

	public double getTheta() {
		return this.matrix[Cylindrical3D.THETA_INDEX][0];
	}

	public void setZ(final double z) {
		this.matrix[Cylindrical3D.Z_INDEX][0] = z;
	}

	public double getZ() {
		return this.matrix[Cylindrical3D.Z_INDEX][0];
	}

	@Override
	public void set(final int row, final int column, final double value) {
		if (row == Cylindrical3D.R_INDEX && column == 0) {
			setR(value);
		} else if (row == Cylindrical3D.THETA_INDEX && column == 0) {
			setTheta(value);
		} else {
			super.set(row, column, value);
		}
	}

	@Override
	public Cylindrical3D clone() {
		final Cylindrical3D clone = new Cylindrical3D();
		clone.copy(this);
		return clone;
	}
}