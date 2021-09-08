package com.tho.thojlib.math.geometry.point;

import com.tho.thojlib.math.geometry.AngleUtils;
import com.tho.thojlib.math.vector.Vector;

public class Spherical3D extends Vector {
	private static final int DIMENSION = 3;

	private static final int RHO_INDEX = 0;
	private static final int THETA_INDEX = 1;
	private static final int PHI_INDEX = 2;

	public Spherical3D() {
		super(Spherical3D.DIMENSION);
	}

	public Spherical3D(final double rho, final double theta, final double phi) {
		super(Spherical3D.DIMENSION);
		set(rho, theta, phi);
	}

	public void set(final double rho, final double theta, final double phi) {
		setTheta(theta);
		setPhi(phi);
		setRho(rho);
	}

	public void setRho(final double rho) {
		this.matrix[Spherical3D.RHO_INDEX][0] = Math.abs(rho);
		if (rho < 0) {
			setTheta(getTheta() + Math.PI);
			setPhi(-getPhi());
		}
	}

	public double getRho() {
		return this.matrix[Spherical3D.RHO_INDEX][0];
	}

	public void setTheta(final double theta) {
		this.matrix[Spherical3D.THETA_INDEX][0] = AngleUtils.foldPi(theta);
	}

	public double getTheta() {
		return this.matrix[Spherical3D.THETA_INDEX][0];
	}

	public void setPhi(final double phi) {
		this.matrix[Spherical3D.PHI_INDEX][0] = AngleUtils.fold(phi, -Math.PI / 2.0, Math.PI / 2.0);
	}

	public double getPhi() {
		return this.matrix[Spherical3D.PHI_INDEX][0];
	}

	@Override
	public void set(final int row, final int column, final double value) {
		if (row == Spherical3D.RHO_INDEX && column == 0) {
			setRho(value);
		} else if (row == Spherical3D.THETA_INDEX && column == 0) {
			setTheta(value);
		} else if (row == Spherical3D.PHI_INDEX && column == 0) {
			setPhi(value);
		} else {
			super.set(row, column, value);
		}
	}

	@Override
	public Spherical3D clone() {
		final Spherical3D clone = new Spherical3D();
		clone.copy(this);
		return clone;
	}
}