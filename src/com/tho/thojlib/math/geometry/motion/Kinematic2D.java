package com.tho.thojlib.math.geometry.motion;

import com.tho.thojlib.math.geometry.point.Cartesian2D;

public class Kinematic2D {

	private final Cartesian2D position;
	private final Cartesian2D velocity;
	private final Cartesian2D acceleration;

	public Kinematic2D() {
		this.position = new Cartesian2D();
		this.velocity = new Cartesian2D();
		this.acceleration = new Cartesian2D();
	}

	public void setPosition(final double x, final double y) {
		this.position.set(x, y);
	}

	public void setVelocity(final double vx, final double vy) {
		this.velocity.set(vx, vy);
	}

	public void setAcceleration(final double ax, final double ay) {
		this.acceleration.set(ax, ay);
	}

	public void extrapolate(final double deltaT) {
		// TODO
	}
}