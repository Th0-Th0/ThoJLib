package com.tho.thojlib.bit;

import org.junit.Assert;
import org.junit.Test;

import com.tho.thojlib.math.MathUtils;

public class TestBitUtils {

	/**
	 * Generate a mask with all bits from 0 to endBit set to 1.
	 */
	private static long fromBitZeroToEndBit(final int endBit) {
		long lResult = 0;
		for (int i = 0; i < endBit + 1; i++) {
			if (i < Integer.SIZE - 1) {
				lResult |= 1 << i;
			} else {
				lResult += MathUtils.pow(2, i);
			}
		}
		return lResult;
	}

	/**
	 * Test : bitmask generation.
	 */
	@Test
	public void testGenerateBitmaskInRange() {
		long mask;

		// Loop over all startBit and endBit possible, plus values out of range
		for (int startBit = -2; startBit < Long.SIZE + 2; startBit++) {
			for (int endBit = -2; endBit < Long.SIZE + 2; endBit++) {
				try {

					// Generate bitmask
					mask = BitUtils.generateBitmaskInRange(startBit, endBit);

					// Check it
					if (startBit > endBit) {
						Assert.assertEquals("startBit = " + startBit + ", endBit = " + endBit, 0L, mask);
					} else if (startBit == endBit) {
						Assert.assertEquals("startBit = " + startBit + ", endBit = " + endBit,
								MathUtils.pow(2, startBit), mask);
					} else {
						Assert.assertEquals("startBit = " + startBit + ", endBit = " + endBit,
								TestBitUtils.fromBitZeroToEndBit(endBit)
										- TestBitUtils.fromBitZeroToEndBit(startBit - 1),
								mask);
					}
				} catch (final IllegalArgumentException e) {
					if (startBit >= 0 && startBit < Long.SIZE && endBit >= 0 && endBit < Long.SIZE) {
						Assert.fail("Should not generate IllegalArgumentException : startBit = " + startBit
								+ ", endBit = " + endBit);
					}
				}
			} // End for() endBit
		} // End for() startBit
	}

	/**
	 * Test : bitmask generation.
	 */
	@Test
	public void testGenerateBitmask() {
		long mask;

		// Loop over all startBit and nbOfBit possible, plus values out of range
		for (int startBit = -2; startBit < Long.SIZE + 2; startBit++) {
			for (int nbOfBit = -2; nbOfBit < Long.SIZE + 2; nbOfBit++) {
				try {

					// Generate bitmask
					mask = BitUtils.generateBitmask(startBit, nbOfBit);

					// Check it
					if (nbOfBit <= 0) {
						Assert.assertEquals("startBit = " + startBit + ", nbOfBit = " + nbOfBit, 0L, mask);
					} else {
						Assert.assertEquals("startBit = " + startBit + ", nbOfBit = " + nbOfBit,
								TestBitUtils.fromBitZeroToEndBit(startBit + nbOfBit - 1)
										- TestBitUtils.fromBitZeroToEndBit(startBit - 1),
								mask);
					}
				} catch (final IllegalArgumentException e) {
					if (startBit >= 0 && startBit < Long.SIZE && startBit + nbOfBit > 0
							&& startBit + nbOfBit < Long.SIZE) {
						Assert.fail("Should not generate IllegalArgumentException : startBit = " + startBit
								+ ", nbOfBit = " + nbOfBit);
					}
				}
			} // End for() nbOfBit
		} // End for() startBit
	}

	@Test
	public void testToByteSizeRoundingUp() {
		Assert.assertEquals(0, BitUtils.toByteSizeRoundingUp(0));
		Assert.assertEquals(1, BitUtils.toByteSizeRoundingUp(1));
		Assert.assertEquals(1, BitUtils.toByteSizeRoundingUp(2));
		Assert.assertEquals(1, BitUtils.toByteSizeRoundingUp(3));
		Assert.assertEquals(1, BitUtils.toByteSizeRoundingUp(4));
		Assert.assertEquals(1, BitUtils.toByteSizeRoundingUp(5));
		Assert.assertEquals(1, BitUtils.toByteSizeRoundingUp(6));
		Assert.assertEquals(1, BitUtils.toByteSizeRoundingUp(7));
		Assert.assertEquals(1, BitUtils.toByteSizeRoundingUp(8));
		Assert.assertEquals(2, BitUtils.toByteSizeRoundingUp(9));
		Assert.assertEquals(2, BitUtils.toByteSizeRoundingUp(10));
		Assert.assertEquals(128, BitUtils.toByteSizeRoundingUp(1024));
		Assert.assertEquals(129, BitUtils.toByteSizeRoundingUp(1025));
	}

	@Test
	public void testBitSizeForRange() {
		Assert.assertEquals(1, BitUtils.optimizedBitSizeForValueInRange(0, 0));
		Assert.assertEquals(1, BitUtils.optimizedBitSizeForValueInRange(0, 1));
		Assert.assertEquals(2, BitUtils.optimizedBitSizeForValueInRange(0, 2));
		Assert.assertEquals(2, BitUtils.optimizedBitSizeForValueInRange(0, 3));
		Assert.assertEquals(3, BitUtils.optimizedBitSizeForValueInRange(0, 4));
		Assert.assertEquals(3, BitUtils.optimizedBitSizeForValueInRange(0, 5));
		Assert.assertEquals(3, BitUtils.optimizedBitSizeForValueInRange(0, 6));
		Assert.assertEquals(3, BitUtils.optimizedBitSizeForValueInRange(0, 7));
		Assert.assertEquals(4, BitUtils.optimizedBitSizeForValueInRange(0, 8));
		Assert.assertEquals(10, BitUtils.optimizedBitSizeForValueInRange(0, 1023));
		Assert.assertEquals(11, BitUtils.optimizedBitSizeForValueInRange(0, 1024));
		Assert.assertEquals(3, BitUtils.optimizedBitSizeForValueInRange(7, 0));
		Assert.assertEquals(4, BitUtils.optimizedBitSizeForValueInRange(8, 0));
		Assert.assertEquals(1, BitUtils.optimizedBitSizeForValueInRange(10, 10));
		Assert.assertEquals(1, BitUtils.optimizedBitSizeForValueInRange(10, 11));
		Assert.assertEquals(2, BitUtils.optimizedBitSizeForValueInRange(10, 12));
	}

	@Test
	public void testOptimizedBitSizeForRange() {
		Assert.assertEquals(1, BitUtils.optimizedBitSizeForValueInRange(0, 0));
		Assert.assertEquals(1, BitUtils.optimizedBitSizeForValueInRange(-1, 0));
		Assert.assertEquals(2, BitUtils.optimizedBitSizeForValueInRange(-1, 1));
		Assert.assertEquals(3, BitUtils.optimizedBitSizeForValueInRange(0, 7));
		Assert.assertEquals(7, BitUtils.optimizedBitSizeForValueInRange(128, 255));
		Assert.assertEquals(8, BitUtils.optimizedBitSizeForValueInRange(128, 256));
	}
}
