package com.tho.thojlib.math.geometry.point;

import com.tho.thojlib.math.geometry.AngleUtils;
import com.tho.thojlib.math.vector.Vector;

public class EulerAngles extends Vector {
	private static final int DIMENSION = 3;

	private static final int PITCH_INDEX = 0;
	private static final int ROLL_INDEX = 1;
	private static final int YAW_INDEX = 2;

	public EulerAngles() {
		super(EulerAngles.DIMENSION);
	}

	public EulerAngles(final double pitch, final double roll, final double yaw) {
		super(EulerAngles.DIMENSION);
		set(pitch, roll, yaw);
	}

	public void set(final double pitch, final double roll, final double yaw) {
		setPitch(pitch);
		setRoll(roll);
		setYaw(yaw);
	}

	public void setPitch(final double pitch) {
		this.matrix[EulerAngles.PITCH_INDEX][0] = AngleUtils.foldPi(pitch);
	}

	public double getPitch() {
		return this.matrix[EulerAngles.PITCH_INDEX][0];
	}

	public void setRoll(final double roll) {
		this.matrix[EulerAngles.ROLL_INDEX][0] = AngleUtils.foldPi(roll);
	}

	public double getRoll() {
		return this.matrix[EulerAngles.ROLL_INDEX][0];
	}

	public void setYaw(final double yaw) {
		this.matrix[EulerAngles.YAW_INDEX][0] = AngleUtils.foldPi(yaw);
	}

	public double getYaw() {
		return this.matrix[EulerAngles.YAW_INDEX][0];
	}

	@Override
	public void set(final int row, final int column, final double value) {
		if (row == EulerAngles.PITCH_INDEX && column == 0) {
			setPitch(value);
		} else if (row == EulerAngles.ROLL_INDEX && column == 0) {
			setRoll(value);
		} else if (row == EulerAngles.YAW_INDEX && column == 0) {
			setYaw(value);
		} else {
			super.set(row, column, value);
		}
	}

	@Override
	public EulerAngles clone() {
		final EulerAngles clone = new EulerAngles();
		clone.copy(this);
		return clone;
	}
}