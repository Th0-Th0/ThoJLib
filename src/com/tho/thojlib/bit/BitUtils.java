package com.tho.thojlib.bit;

import com.tho.thojlib.math.MathUtils;

public class BitUtils {
	public static final int BITS_PER_BYTE = 8;

	private BitUtils() {
		// Nothing to do.
	}

	/**
	 * Generate a bit mask (start to end bit). Max 64 bits.
	 */
	public static long generateBitmaskInRange(final int startBit, final int endBit) {

		// Check arguments
		if (startBit >= Long.SIZE || startBit < 0 || endBit >= Long.SIZE || endBit < 0) {
			throw new IllegalArgumentException("Can't generate bitmask from bit " + startBit + " to bit " + endBit);
		}
		if (startBit > endBit) {
			return 0L;
		}

		// Generate mask from 0 to endBit
		final long lowerMask = Long.highestOneBit(-1L) >> 63 >>> 63 - endBit;

		// Generate mask from startBit to bit 64
		final long higherMask = Long.highestOneBit(-1L) >> 63 << startBit;

		return higherMask & lowerMask;
	}

	/**
	 * Generate a bit mask (nb of bits). Max 6 bits.
	 */
	public static long generateBitmask(final int startBit, final int nbOfBit) {
		return BitUtils.generateBitmaskInRange(startBit, startBit + nbOfBit - 1);
	}

	/**
	 * Rounds up a bit size to a byte size.
	 */
	public static int toByteSizeRoundingUp(final int bitSize) {
		return MathUtils.modPowerOfTwo(bitSize, BitUtils.BITS_PER_BYTE) == 0 ? bitSize / BitUtils.BITS_PER_BYTE
				: bitSize / BitUtils.BITS_PER_BYTE + 1;
	}

	/**
	 * Compute number of bits needed to define a range of value.
	 */
	public static int optimizedBitSizeForValueInRange(final int minValue, final int maxValue) {
		if (minValue == maxValue) {
			return 1;
		}

		int range = maxValue > minValue ? maxValue - minValue : minValue - maxValue;
		int bitSize = 0;
		while (range > 0) {
			bitSize++;
			range = range >> 1;
		}

		return bitSize;
	}
}
