package com.tho.thojlib.array;

public class ArrayUtils {

	private ArrayUtils() {
		// Utility class
	}

	/**
	 * Copy 2D array of int.
	 */
	public static void arrayCopy(final int[][] src, final int[][] dst) {
		for (int i = 0; i < src.length; i++) {
			System.arraycopy(src[i], 0, dst[i], 0, src[i].length);
		}
	}
}