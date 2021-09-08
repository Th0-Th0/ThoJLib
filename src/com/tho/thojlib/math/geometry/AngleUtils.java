package com.tho.thojlib.math.geometry;

import com.tho.thojlib.math.MathUtils;

public class AngleUtils {

	private AngleUtils() {
		// Utility class
	}

	/**
	 * Fold an angle inside [minAngle,maxAngle[.
	 */
	public static double fold(final double angle, final double minAngle, final double maxAngle) {
		double res;
		if (angle < minAngle) {
			res = MathUtils.mod(angle - minAngle, maxAngle - minAngle) + minAngle;
		} else if (angle >= maxAngle) {
			res = (angle - minAngle) % (maxAngle - minAngle) + minAngle;
		} else {
			res = angle;
		}
		return res;
	}

	/**
	 * Fold an angle (rad) inside [-pi,+pi[.
	 */
	public static double foldPi(final double angle) {
		return AngleUtils.fold(angle, -Math.PI, Math.PI);
	}
}