package com.tho.thojlib.math;

import org.junit.Assert;
import org.junit.Test;

public class TestMathUtils {

	@Test
	public void testPerfectSquare() {
		for (int i = -10; i < 110; i++) {
			if (i == 0 || i == 1 || i == 4 || i == 9 || i == 16 || i == 25 || i == 36 || i == 49 || i == 64 || i == 81 || i == 100) {
				Assert.assertTrue(MathUtils.isPerfectSquare(i));
			} else {
				Assert.assertFalse(MathUtils.isPerfectSquare(i));
			}
		}
		Assert.assertFalse(MathUtils.isPerfectSquare(100000001));
		Assert.assertTrue(MathUtils.isPerfectSquare(100000000));
	}
}
