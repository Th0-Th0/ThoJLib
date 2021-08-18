package com.tho.thojlib.math;

public class MathUtils {

	private MathUtils() {
		// Nothing to do
	}

	/**
	 * Power for type long.
	 */
	public static long pow(final long value, final long power) {
		long result = 1;
		for (int i = 1; i <= power; i++) {
			result *= value;
		}
		return result;
	}

	/**
	 * Optimized modulo for power of 2.
	 * Warning : the arg mod must be a power of 2.
	 */
	public static int modPowerOfTwo(final int value, final int mod) {
		return value & (mod - 1);
	}

	/**
	 * Generates a random int in the specified range (bounds included).
	 */
	public static int generateRandomInt(final int min, final int max) {
		return min + (int) ((Math.abs(max - min) + 1) * Math.random());
	}

	/**
	 * Returns true if the argument is a perfect square.
	 */
	public static boolean isPerfectSquare(final int value) {
		if (value < 0) {
			return false;
		}

		final int sqrt = (int) Math.sqrt(value);
		return sqrt * sqrt == value;
	}
} 