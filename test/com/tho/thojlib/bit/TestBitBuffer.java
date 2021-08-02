package com.tho.thojlib.bit;

import org.junit.Assert;
import org.junit.Test;

import com.tho.thojlib.math.MathUtils;

public class TestBitBuffer {

	/**
	 * Convert a binary of string to an array of boolean (1 is true, 0 is false).
	 */
	private static void fromBinaryOfStringToArrayOfBoolean(final String strBinary, final boolean[] b) {
		for (int i = 0; i < strBinary.length(); i++) {
			b[i] = strBinary.getBytes()[strBinary.length() - 1 - i] == '1';
		}
	}

	/**
	 * Test : store boolean(s) in a BitBuffer (1bit).
	 */
	@Test
	public void testBoolean() {
		// 64 max for this test
		final int nbOfBoolean = 10;

		final BitBuffer buffer = new BitBuffer(nbOfBoolean);
		final boolean[] expectedValues = new boolean[nbOfBoolean];
		final long nbOfValue = MathUtils.pow(2, nbOfBoolean);

		// Loop over all values (nbOfBoolean bits)
		for (long expectedValue = 0; expectedValue < nbOfValue; expectedValue++) {

			// Compute current expected value
			TestBitBuffer.fromBinaryOfStringToArrayOfBoolean(Long.toBinaryString(expectedValue), expectedValues);

			// Reset position
			buffer.resetBitPosition();

			// Store expected value in the BitBuffer
			for (int i = 0; i < nbOfBoolean; i++) {
				buffer.putBoolean(expectedValues[i]);
			}

			// Reset position
			buffer.resetBitPosition();

			// Read value from BitBuffer, check it
			for (int i = 0; i < nbOfBoolean; i++) {
				Assert.assertEquals("1 - Incorrect boolean at index " + i + " (expected value=" + expectedValue + ")",
						expectedValues[i], buffer.getBoolean());
			}

			// Reset position
			buffer.resetBitPosition();

			// Read value from BitBuffer, check it
			boolean[] valuesFromBitBuffer = buffer.getBooleanArray(nbOfBoolean);
			for (int i = 0; i < nbOfBoolean; i++) {
				Assert.assertEquals("2 - Incorrect boolean at index " + i + " (expected value=" + expectedValue + ")",
						expectedValues[i], valuesFromBitBuffer[i]);
			}

			// Reset position
			buffer.resetBitPosition();

			// Store expected value in the BitBuffer
			buffer.putBooleanArray(expectedValues);

			// Reset position
			buffer.resetBitPosition();

			// Read value from BitBuffer, check it
			for (int i = 0; i < nbOfBoolean; i++) {
				Assert.assertEquals("3 - Incorrect boolean at index " + i + " (expected value=" + expectedValue + ")",
						expectedValues[i], buffer.getBoolean());
			}

			// Reset position
			buffer.resetBitPosition();

			// Read value from BitBuffer, check it
			valuesFromBitBuffer = buffer.getBooleanArray(nbOfBoolean);
			for (int i = 0; i < nbOfBoolean; i++) {
				Assert.assertEquals("4 - Incorrect boolean at index " + i + " (expected value=" + expectedValue + ")",
						expectedValues[i], valuesFromBitBuffer[i]);
			}
		}
	}

	/**
	 * Test : store byte(s) in a BitBuffer (8bits).
	 */
	@Test
	public void testByte() {
		final int nbOfByte = 10;
		final byte[] testedValues = new byte[] { Byte.MAX_VALUE, Byte.MIN_VALUE, 0, -1, 1, -50, 100 };

		final BitBuffer buffer = new BitBuffer(nbOfByte * Byte.SIZE + 8);
		final byte[] expectedValues = new byte[nbOfByte];

		// Loop over all init position possible in a byte
		for (int initialPositionInBuffer = 0; initialPositionInBuffer < 8; initialPositionInBuffer++) {

			// Loop over all values
			for (int idxTestedValue = 0; idxTestedValue < testedValues.length; idxTestedValue++) {

				for (int i = 0; i < nbOfByte; i++) {
					expectedValues[i] = testedValues[idxTestedValue];
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Store expected value in the BitBuffer
				for (int i = 0; i < nbOfByte; i++) {
					buffer.putByte(expectedValues[i]);
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				for (int i = 0; i < nbOfByte; i++) {
					Assert.assertEquals("1 - Incorrect byte at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i], buffer.getByte());
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				byte[] valuesFromBitBuffer = buffer.getByteArray(nbOfByte);
				for (int i = 0; i < nbOfByte; i++) {
					Assert.assertEquals("2 - Incorrect byte at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i], valuesFromBitBuffer[i]);
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Store expected value in the BitBuffer
				buffer.putByteArray(expectedValues);

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				for (int i = 0; i < nbOfByte; i++) {
					Assert.assertEquals("3 - Incorrect byte at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i], buffer.getByte());
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				valuesFromBitBuffer = buffer.getByteArray(nbOfByte);
				for (int i = 0; i < nbOfByte; i++) {
					Assert.assertEquals("4 - Incorrect short at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i], valuesFromBitBuffer[i]);
				}
			}
		}
	}

	/**
	 * Test : store char(s) in a BitBuffer (16bits).
	 */
	@Test
	public void testChar() {
		final int nbOfChar = 10;
		final char[] testedValues = new char[] { Character.MAX_VALUE, Character.MIN_VALUE, 'a', 'z', 'A', 'Z', '0', '9',
				'\n', '@' };

		final BitBuffer buffer = new BitBuffer(nbOfChar * Character.SIZE + 8);
		final char[] expectedValues = new char[nbOfChar];

		// Loop over all init position possible in a byte
		for (int initialPositionInBuffer = 0; initialPositionInBuffer < 8; initialPositionInBuffer++) {

			// Loop over all values
			for (int idxTestedValue = 0; idxTestedValue < testedValues.length; idxTestedValue++) {

				for (int i = 0; i < nbOfChar; i++) {
					expectedValues[i] = testedValues[idxTestedValue];
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Store expected value in the BitBuffer
				for (int i = 0; i < nbOfChar; i++) {
					buffer.putChar(expectedValues[i]);
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				for (int i = 0; i < nbOfChar; i++) {
					Assert.assertEquals("1 - Incorrect char at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i], buffer.getChar());
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				char[] valuesFromBitBuffer = buffer.getCharArray(nbOfChar);
				for (int i = 0; i < nbOfChar; i++) {
					Assert.assertEquals("2 - Incorrect char at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i], valuesFromBitBuffer[i]);
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Store expected value in the BitBuffer
				buffer.putCharArray(expectedValues);

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				for (int i = 0; i < nbOfChar; i++) {
					Assert.assertEquals("3 - Incorrect char at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i], buffer.getChar());
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				valuesFromBitBuffer = buffer.getCharArray(nbOfChar);
				for (int i = 0; i < nbOfChar; i++) {
					Assert.assertEquals("4 - Incorrect char at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i], valuesFromBitBuffer[i]);
				}
			}
		}
	}

	/**
	 * Test : store short(s) in a BitBuffer (16bits).
	 */
	@Test
	public void testShort() {
		final int nbOfShort = 10;
		final short[] testedValues = new short[] { Short.MAX_VALUE, Short.MIN_VALUE, 0, -1, 1, -15611, 8941 };

		final BitBuffer buffer = new BitBuffer(nbOfShort * Short.SIZE + 8);
		final short[] expectedValues = new short[nbOfShort];

		// Loop over all init position possible in a byte
		for (int initialPositionInBuffer = 0; initialPositionInBuffer < 8; initialPositionInBuffer++) {

			// Loop over all values
			for (int idxTestedValue = 0; idxTestedValue < testedValues.length; idxTestedValue++) {

				for (int i = 0; i < nbOfShort; i++) {
					expectedValues[i] = testedValues[idxTestedValue];
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Store expected value in the BitBuffer
				for (int i = 0; i < nbOfShort; i++) {
					buffer.putShort(expectedValues[i]);
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				for (int i = 0; i < nbOfShort; i++) {
					Assert.assertEquals("1 - Incorrect short at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i], buffer.getShort());
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				short[] valuesFromBitBuffer = buffer.getShortArray(nbOfShort);
				for (int i = 0; i < nbOfShort; i++) {
					Assert.assertEquals("2 - Incorrect short at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i], valuesFromBitBuffer[i]);
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Store expected value in the BitBuffer
				buffer.putShortArray(expectedValues);

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				for (int i = 0; i < nbOfShort; i++) {
					Assert.assertEquals("3 - Incorrect short at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i], buffer.getShort());
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				valuesFromBitBuffer = buffer.getShortArray(nbOfShort);
				for (int i = 0; i < nbOfShort; i++) {
					Assert.assertEquals("4 - Incorrect short at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i], valuesFromBitBuffer[i]);
				}
			}
		}
	}

	/**
	 * Test : store int(s) in a BitBuffer (32bits).
	 */
	@Test
	public void testInt() {
		final int nbOfInt = 10;
		final int[] testedValues = new int[] { Integer.MAX_VALUE, Integer.MIN_VALUE, 0, -1, 1, -156115, 894153 };

		final BitBuffer buffer = new BitBuffer(nbOfInt * Integer.SIZE + 8);
		final int[] expectedValues = new int[nbOfInt];

		// Loop over all init position possible in a byte
		for (int initialPositionInBuffer = 0; initialPositionInBuffer < 8; initialPositionInBuffer++) {

			// Loop over all values
			for (int idxTestedValue = 0; idxTestedValue < testedValues.length; idxTestedValue++) {

				for (int i = 0; i < nbOfInt; i++) {
					expectedValues[i] = testedValues[idxTestedValue];
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Store expected value in the BitBuffer
				for (int i = 0; i < nbOfInt; i++) {
					buffer.putInt(expectedValues[i]);
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				for (int i = 0; i < nbOfInt; i++) {
					Assert.assertEquals("1 - Incorrect int at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i], buffer.getInt());
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				int[] valuesFromBitBuffer = buffer.getIntArray(nbOfInt);
				for (int i = 0; i < nbOfInt; i++) {
					Assert.assertEquals("2 - Incorrect int at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i], valuesFromBitBuffer[i]);
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Store expected value in the BitBuffer
				buffer.putIntArray(expectedValues);

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				for (int i = 0; i < nbOfInt; i++) {
					Assert.assertEquals("3 - Incorrect int at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i], buffer.getInt());
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				valuesFromBitBuffer = buffer.getIntArray(nbOfInt);
				for (int i = 0; i < nbOfInt; i++) {
					Assert.assertEquals("4 - Incorrect int at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i], valuesFromBitBuffer[i]);
				}
			}
		}
	}

	/**
	 * Test : store long(s) in a BitBuffer (64bits).
	 */
	@Test
	public void testLong() {
		final int nbOfLong = 10;
		final long[] testedValues = new long[] { Long.MAX_VALUE, Long.MIN_VALUE, 0, -1, 1, -156115, 894153 };

		final BitBuffer buffer = new BitBuffer(nbOfLong * Long.SIZE + 8);
		final long[] expectedValues = new long[nbOfLong];

		// Loop over all init position possible in a byte
		for (int initialPositionInBuffer = 0; initialPositionInBuffer < 8; initialPositionInBuffer++) {

			// Loop over all values
			for (int idxTestedValue = 0; idxTestedValue < testedValues.length; idxTestedValue++) {

				for (int i = 0; i < nbOfLong; i++) {
					expectedValues[i] = testedValues[idxTestedValue];
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Store expected value in the BitBuffer
				for (int i = 0; i < nbOfLong; i++) {
					buffer.putLong(expectedValues[i]);
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				for (int i = 0; i < nbOfLong; i++) {
					Assert.assertEquals("1 - Incorrect long at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i], buffer.getLong());
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				long[] valuesFromBitBuffer = buffer.getLongArray(nbOfLong);
				for (int i = 0; i < nbOfLong; i++) {
					Assert.assertEquals("2 - Incorrect long at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i], valuesFromBitBuffer[i]);
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Store expected value in the BitBuffer
				buffer.putLongArray(expectedValues);

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				for (int i = 0; i < nbOfLong; i++) {
					Assert.assertEquals("3 - Incorrect long at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i], buffer.getLong());
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				valuesFromBitBuffer = buffer.getLongArray(nbOfLong);
				for (int i = 0; i < nbOfLong; i++) {
					Assert.assertEquals("4 - Incorrect long at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i], valuesFromBitBuffer[i]);
				}
			}
		}
	}

	/**
	 * Test : store float(s) in a BitBuffer (32bits).
	 */
	@Test
	public void testFloat() {
		final int nbOfFloat = 10;
		final float[] testedValues = new float[] { Float.MAX_VALUE, Float.MIN_VALUE, 0.0f, -1.0f, 1.0f, -156115.0f,
				894153.0f };

		final BitBuffer buffer = new BitBuffer(nbOfFloat * Float.SIZE + 8);
		final float[] expectedValues = new float[nbOfFloat];

		// Loop over all init position possible in a byte
		for (int initialPositionInBuffer = 0; initialPositionInBuffer < 8; initialPositionInBuffer++) {

			// Loop over all values
			for (int idxTestedValue = 0; idxTestedValue < testedValues.length; idxTestedValue++) {

				for (int i = 0; i < nbOfFloat; i++) {
					expectedValues[i] = testedValues[idxTestedValue];
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Store expected value in the BitBuffer
				for (int i = 0; i < nbOfFloat; i++) {
					buffer.putFloat(expectedValues[i]);
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				for (int i = 0; i < nbOfFloat; i++) {
					Assert.assertEquals("1 - Incorrect float at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i], buffer.getFloat(), 0.0f);
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				float[] valuesFromBitBuffer = buffer.getFloatArray(nbOfFloat);
				for (int i = 0; i < nbOfFloat; i++) {
					Assert.assertEquals("2 - Incorrect float at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i], valuesFromBitBuffer[i], 0.0f);
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Store expected value in the BitBuffer
				buffer.putFloatArray(expectedValues);

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				for (int i = 0; i < nbOfFloat; i++) {
					Assert.assertEquals("3 - Incorrect float at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i], buffer.getFloat(), 0.0f);
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				valuesFromBitBuffer = buffer.getFloatArray(nbOfFloat);
				for (int i = 0; i < nbOfFloat; i++) {
					Assert.assertEquals("4 - Incorrect float at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i], valuesFromBitBuffer[i], 0.0f);
				}
			}
		}
	}

	/**
	 * Test : store double(s) in a BitBuffer (64bits).
	 */
	@Test
	public void testDouble() {
		final int nbOfDouble = 10;
		final double[] testedValues = new double[] { Double.MAX_VALUE, Double.MIN_VALUE, 0.0, -1.0, 1.0, -156115.0,
				894153.0 };

		final BitBuffer buffer = new BitBuffer(nbOfDouble * Double.SIZE + 8);
		final double[] expectedValues = new double[nbOfDouble];

		// Loop over all init position possible in a byte
		for (int initialPositionInBuffer = 0; initialPositionInBuffer < 8; initialPositionInBuffer++) {

			// Loop over all values
			for (int idxTestedValue = 0; idxTestedValue < testedValues.length; idxTestedValue++) {

				for (int i = 0; i < nbOfDouble; i++) {
					expectedValues[i] = testedValues[idxTestedValue];
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Store expected value in the BitBuffer
				for (int i = 0; i < nbOfDouble; i++) {
					buffer.putDouble(expectedValues[i]);
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				for (int i = 0; i < nbOfDouble; i++) {
					Assert.assertEquals("1 - Incorrect double at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i], buffer.getDouble(), 0.0);
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				double[] valuesFromBitBuffer = buffer.getDoubleArray(nbOfDouble);
				for (int i = 0; i < nbOfDouble; i++) {
					Assert.assertEquals("2 - Incorrect double at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i], valuesFromBitBuffer[i], 0.0);
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Store expected value in the BitBuffer
				buffer.putDoubleArray(expectedValues);

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				for (int i = 0; i < nbOfDouble; i++) {
					Assert.assertEquals("3 - Incorrect double at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i], buffer.getDouble(), 0.0);
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				valuesFromBitBuffer = buffer.getDoubleArray(nbOfDouble);
				for (int i = 0; i < nbOfDouble; i++) {
					Assert.assertEquals("4 - Incorrect double at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i], valuesFromBitBuffer[i], 0.0);
				}
			}
		}
	}

	/**
	 * Test : store string(s) in a BitBuffer.
	 */
	@Test
	public void testString() {
		final int nbOfStrings = 10;
		final String[] testedValues = new String[] { "test", "test1", " ", "", "123456", "a", "\n" };

		final BitBuffer buffer = new BitBuffer(nbOfStrings * 10 * Character.SIZE);
		final String[] expectedValues = new String[nbOfStrings];

		// Loop over all init position possible in a byte
		for (int initialPositionInBuffer = 0; initialPositionInBuffer < 8; initialPositionInBuffer++) {

			// Loop over all values
			for (int idxTestedValue = 0; idxTestedValue < testedValues.length; idxTestedValue++) {

				for (int i = 0; i < nbOfStrings; i++) {
					expectedValues[i] = testedValues[idxTestedValue];
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Store expected value in the BitBuffer
				for (int i = 0; i < nbOfStrings; i++) {
					buffer.putString(expectedValues[i]);
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				for (int i = 0; i < nbOfStrings; i++) {
					Assert.assertEquals("1 - Incorrect String at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i],
							buffer.getString(expectedValues[i].length()));
				}

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Store expected value in the BitBuffer
				buffer.putStringArray(expectedValues);

				// Set position
				buffer.setBitPosition(initialPositionInBuffer);

				// Read value from BitBuffer, check it
				for (int i = 0; i < nbOfStrings; i++) {
					Assert.assertEquals("2 - Incorrect String at index " + i + " (expected value="
							+ testedValues[idxTestedValue] + ")", expectedValues[i],
							buffer.getString(expectedValues[i].length()));
				}
			}
		}
	}

	/**
	 * Test : store various type in a BitBuffer (64bits).
	 */
	@Test
	public void testVariousTypes() {
		final BitBuffer buffer = new BitBuffer(
				1 + Character.SIZE + Short.SIZE + Integer.SIZE + Long.SIZE + Float.SIZE + Double.SIZE + 8);
		final boolean expectedBoolean = true;
		final char expectedChar = Character.MAX_VALUE / 2;
		final short expectedShort = Short.MAX_VALUE / 2;
		final int expectedInt = Integer.MAX_VALUE / 2;
		final long expectedLong = Long.MAX_VALUE / 2L;
		final float expectedFloat = Float.MAX_VALUE / 2.0f;
		final double expectedDouble = Double.MAX_VALUE / 2.0;

		// Loop over all init position possible in a byte
		for (int initialPositionInBuffer = 0; initialPositionInBuffer < 8; initialPositionInBuffer++) {

			// Set position
			buffer.setBitPosition(initialPositionInBuffer);

			// Store expected value in the BitBuffer
			buffer.putBoolean(expectedBoolean);
			buffer.putChar(expectedChar);
			buffer.putShort(expectedShort);
			buffer.putInt(expectedInt);
			buffer.putLong(expectedLong);
			buffer.putFloat(expectedFloat);
			buffer.putDouble(expectedDouble);

			// Set position
			buffer.setBitPosition(initialPositionInBuffer);

			// Read value from BitBuffer, check it
			Assert.assertEquals("Incorrect boolean", expectedBoolean, buffer.getBoolean());
			Assert.assertEquals("Incorrect char", expectedChar, buffer.getChar());
			Assert.assertEquals("Incorrect short", expectedShort, buffer.getShort());
			Assert.assertEquals("Incorrect int", expectedInt, buffer.getInt());
			Assert.assertEquals("Incorrect long", expectedLong, buffer.getLong());
			Assert.assertEquals("Incorrect float", expectedFloat, buffer.getFloat(), 0.0);
			Assert.assertEquals("Incorrect double", expectedDouble, buffer.getDouble(), 0.0);
		}
	}

	@Test
	public void testIsEqualAndCopy() {
		final BitBuffer bb1 = new BitBuffer(1024);
		final BitBuffer bb2 = new BitBuffer(1024);

		Assert.assertTrue(bb1.isBitDataEqual(bb2));
		Assert.assertTrue(bb1.isByteDataEqual(bb2));

		bb1.putByte(Byte.MAX_VALUE);
		Assert.assertFalse(bb1.isBitDataEqual(bb2));
		Assert.assertFalse(bb1.isByteDataEqual(bb2));

		bb2.putByte(Byte.MAX_VALUE);
		Assert.assertTrue(bb1.isBitDataEqual(bb2));
		Assert.assertTrue(bb1.isByteDataEqual(bb2));

		bb1.putBoolean(true);
		Assert.assertFalse(bb1.isBitDataEqual(bb2));
		Assert.assertFalse(bb1.isByteDataEqual(bb2));

		bb2.putBoolean(true);
		Assert.assertTrue(bb1.isBitDataEqual(bb2));
		Assert.assertTrue(bb1.isByteDataEqual(bb2));

		bb1.putBoolean(false);
		Assert.assertFalse(bb1.isBitDataEqual(bb2));
		Assert.assertTrue(bb1.isByteDataEqual(bb2));

		bb2.putBoolean(false);
		Assert.assertTrue(bb1.isBitDataEqual(bb2));
		Assert.assertTrue(bb1.isByteDataEqual(bb2));

		bb1.putInt(-1);
		Assert.assertFalse(bb1.isBitDataEqual(bb2));
		Assert.assertFalse(bb1.isByteDataEqual(bb2));

		bb2.putInt(-1);
		Assert.assertTrue(bb1.isBitDataEqual(bb2));
		Assert.assertTrue(bb1.isByteDataEqual(bb2));

		bb1.putChar('A');
		Assert.assertFalse(bb1.isBitDataEqual(bb2));
		Assert.assertFalse(bb1.isByteDataEqual(bb2));

		bb2.putChar('A');
		Assert.assertTrue(bb1.isBitDataEqual(bb2));
		Assert.assertTrue(bb1.isByteDataEqual(bb2));

		bb1.clear();
		bb1.putBoolean(true);
		bb1.putBoolean(false);
		bb1.putBoolean(true);
		bb1.putString("BitBufferTest.testIsEqualAndCopy");
		Assert.assertFalse(bb1.isBitDataEqual(bb2));
		Assert.assertFalse(bb1.isByteDataEqual(bb2));

		bb2.copyByteDataFrom(bb1);
		Assert.assertTrue(bb1.isByteDataEqual(bb2));

		bb1.clear();
		bb1.putBoolean(true);
		bb1.putBoolean(false);
		bb1.putBoolean(true);
		bb1.putString("testIsEqualAndCopy BitBufferTest bla");

		bb2.copyBitDataFrom(bb1);
		Assert.assertTrue(bb1.isBitDataEqual(bb2));
		Assert.assertTrue(bb1.isByteDataEqual(bb2));
	}
}
