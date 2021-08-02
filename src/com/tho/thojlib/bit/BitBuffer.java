package com.tho.thojlib.bit;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.BufferOverflowException;

import com.tho.thojlib.math.MathUtils;

public class BitBuffer {
	private final byte[] byteBuffer;
	private final int bitCapacity;
	private int bitPosition;
	private int maxBitPosition;
	private final IntStack positionStack;

	public BitBuffer(final int bitCapacity) {
		this.bitCapacity = bitCapacity;
		this.bitPosition = 0;
		this.maxBitPosition = 0;
		this.byteBuffer = new byte[BitUtils.toByteSizeRoundingUp(this.bitCapacity)];
		this.positionStack = new IntStack(100);
	}

	public byte[] getByteBuffer() {
		return this.byteBuffer;
	}

	public int getBytePosition() {
		return BitUtils.toByteSizeRoundingUp(this.bitPosition);
	}

	public int getMaxBytePosition() {
		return BitUtils.toByteSizeRoundingUp(this.maxBitPosition);
	}

	public int getBitPosition() {
		return this.bitPosition;
	}

	public int getMaxBitPosition() {
		return this.maxBitPosition;
	}

	public int getBitCapacity() {
		return this.bitCapacity;
	}

	public void clear() {
		this.bitPosition = 0;
		this.maxBitPosition = 0;
	}

	public void resetBitPosition() {
		this.bitPosition = 0;
	}

	public void setBitPosition(final int newPosition) {
		// Check arguments
		if (newPosition < 0 || newPosition > this.bitCapacity) {
			throw new BufferOverflowException();
		}

		this.bitPosition = newPosition;

		// Set maxPosition if necessary
		if (this.bitPosition > this.maxBitPosition) {
			this.maxBitPosition = this.bitPosition;
		}
	}

	public void pushBitPosition() {
		this.positionStack.push(this.bitPosition);
	}

	public void popBitPosition() {
		this.bitPosition = this.positionStack.pop();
	}

	public void clearBitPositionStack() {
		this.positionStack.clear();
	}

	public void copyByteDataFrom(final BitBuffer bb) {
		clear();
		putByteArray(bb.getByteBuffer(), bb.getMaxBytePosition());
	}

	public void copyBitDataFrom(final BitBuffer bb) {
		clear();
		int i;
		for (i = 0; i < bb.getMaxBytePosition() - 1; i++) {
			putByte(bb.getByteBuffer()[i]);
		}
		final int nbOfRemainginBits = MathUtils.modPowerOfTwo(
				BitUtils.BITS_PER_BYTE - (i * BitUtils.BITS_PER_BYTE - bb.getMaxBitPosition()), BitUtils.BITS_PER_BYTE);
		put(bb.getByteBuffer()[i] & BitUtils.generateBitmaskInRange(0, nbOfRemainginBits), nbOfRemainginBits);
	}

	/**
	 * Fill the BitBuffer with an InputStream. Returns number of bytes read.
	 */
	public int fromInputStream(final InputStream is) {
		int totalByte = 0;
		clear();
		int byteRead;
		try {
			byteRead = is.read(this.byteBuffer, 0, is.available());

			if (byteRead > 0) {
				totalByte += byteRead;
				this.bitPosition = byteRead * BitUtils.BITS_PER_BYTE;
				this.maxBitPosition = this.bitPosition;
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}

		return totalByte;
	}

	/**
	 * Fill the OutputStream with the BitBuffer data.
	 */
	public void toOutputStream(final OutputStream os) {
		final int max = BitUtils.toByteSizeRoundingUp(this.maxBitPosition);
		try {
			os.write(this.byteBuffer, 0, max);
			this.bitPosition = max;
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isByteDataEqual(final BitBuffer bb) {

		if (bb.getMaxBytePosition() != getMaxBytePosition()) {
			return false;
		}

		return isEqual(bb, getMaxBytePosition());
	}

	public boolean isBitDataEqual(final BitBuffer bb) {

		if (bb.getMaxBitPosition() != getMaxBitPosition()) {
			return false;
		}

		return isEqual(bb, getMaxBitPosition());
	}

	private boolean isEqual(final BitBuffer bb, final int nbOfBits) {
		final int nbOfByte = BitUtils.toByteSizeRoundingUp(nbOfBits);
		int idxByte = 0;
		boolean isDiff = false;
		while (idxByte < nbOfByte - 1 && !isDiff) {
			isDiff = bb.getByteBuffer()[idxByte] != getByteBuffer()[idxByte];
			idxByte++;
		}
		final int nbOfRemainginBits = MathUtils.modPowerOfTwo(
				BitUtils.BITS_PER_BYTE - (idxByte * BitUtils.BITS_PER_BYTE - nbOfBits), BitUtils.BITS_PER_BYTE);
		isDiff = (bb.getByteBuffer()[idxByte]
				& BitUtils.generateBitmaskInRange(0, nbOfRemainginBits)) != (getByteBuffer()[idxByte]
						& BitUtils.generateBitmaskInRange(0, nbOfRemainginBits));

		return !isDiff;
	}

	public void put(final long data, final int size) {
		// Check arguments
		if (size > Long.SIZE) {
			throw new IllegalArgumentException("Can't put more than " + Long.SIZE + "bits (arg is " + size + "bits)");
		}
		if (this.bitPosition + size > this.bitCapacity) {
			throw new BufferOverflowException();
		}

		int remainingBitToWrite = size;
		int nbOfBitToWrite;

		// Loop while there is remaining bit(s) to put
		while (remainingBitToWrite > 0) {

			// Compute number of bit(s) to write in this part of the buffer
			if (remainingBitToWrite > BitUtils.BITS_PER_BYTE
					- MathUtils.modPowerOfTwo(this.bitPosition, BitUtils.BITS_PER_BYTE)) {
				nbOfBitToWrite = BitUtils.BITS_PER_BYTE
						- MathUtils.modPowerOfTwo(this.bitPosition, BitUtils.BITS_PER_BYTE);
			} else {
				nbOfBitToWrite = remainingBitToWrite;
			}

			// Reset bit(s) in this part of the buffer
			this.byteBuffer[this.bitPosition >> 3] &= ~(BitUtils.generateBitmask(0, nbOfBitToWrite) << MathUtils
					.modPowerOfTwo(this.bitPosition, BitUtils.BITS_PER_BYTE));

			// Set bit(s) in this part of the buffer according to argument
			this.byteBuffer[this.bitPosition >> 3] |= (data
					& BitUtils.generateBitmask(size - remainingBitToWrite, nbOfBitToWrite)) >> size
							- remainingBitToWrite << MathUtils.modPowerOfTwo(this.bitPosition, BitUtils.BITS_PER_BYTE);

			this.bitPosition += nbOfBitToWrite;
			remainingBitToWrite -= nbOfBitToWrite;
		}

		if (this.bitPosition > this.maxBitPosition) {
			this.maxBitPosition = this.bitPosition;
		}
	}

	public void putBoolean(final boolean value) {
		put(value ? BitUtils.BOOLEAN_TRUE : BitUtils.BOOLEAN_FALSE, BitUtils.BOOLEAN_BIT_SIZE);
	}

	public boolean getBoolean() {
		return get(BitUtils.BOOLEAN_BIT_SIZE) == BitUtils.BOOLEAN_TRUE;
	}

	public void putBooleanArray(final boolean[] values) {
		putBooleanArray(values, values.length);
	}

	public void putBooleanArray(final boolean[] values, final int size) {
		for (int i = 0; i < size; i++) {
			putBoolean(values[i]);
		}
	}

	public boolean[] getBooleanArray(final int nbOfBoolean) {
		final boolean[] result = new boolean[nbOfBoolean];
		for (int idxInt = 0; idxInt < nbOfBoolean; idxInt++) {
			result[idxInt] = getBoolean();
		}
		return result;
	}

	public void putByte(final byte value) {
		put(value, Byte.SIZE);
	}

	public byte getByte() {
		return (byte) get(Byte.SIZE);
	}

	public void putByteArray(final byte[] values) {
		putByteArray(values, values.length);
	}

	public void putByteArray(final byte[] values, final int size) {
		for (int idxByte = 0; idxByte < size; idxByte++) {
			putByte(values[idxByte]);
		}
	}

	public byte[] getByteArray(final int nbOfByte) {
		final byte[] result = new byte[nbOfByte];
		for (int idxByte = 0; idxByte < nbOfByte; idxByte++) {
			result[idxByte] = getByte();
		}
		return result;
	}

	public void putChar(final char value) {
		put(value, Character.SIZE);
	}

	public char getChar() {
		return (char) get(Character.SIZE);
	}

	public void putCharArray(final char[] values) {
		putCharArray(values, values.length);
	}

	public void putCharArray(final char[] values, final int size) {
		for (int idxChar = 0; idxChar < size; idxChar++) {
			putChar(values[idxChar]);
		}
	}

	public char[] getCharArray(final int nbOfChar) {
		final char[] result = new char[nbOfChar];
		for (int idxChar = 0; idxChar < nbOfChar; idxChar++) {
			result[idxChar] = getChar();
		}
		return result;
	}

	public void putShort(final short value) {
		put(value, Short.SIZE);
	}

	public short getShort() {
		return (short) get(Short.SIZE);
	}

	public void putShortArray(final short[] values) {
		putShortArray(values, values.length);
	}

	public void putShortArray(final short[] values, final int size) {
		for (int i = 0; i < size; i++) {
			putShort(values[i]);
		}
	}

	public short[] getShortArray(final int nbOfShort) {
		final short[] result = new short[nbOfShort];
		for (int idxShort = 0; idxShort < nbOfShort; idxShort++) {
			result[idxShort] = getShort();
		}
		return result;
	}

	public void putInt(final int value) {
		put(value, Integer.SIZE);
	}

	public int getInt() {
		return (int) get(Integer.SIZE);
	}

	public void putIntArray(final int[] values) {
		putIntArray(values, values.length);
	}

	public void putIntArray(final int[] values, final int size) {
		for (int i = 0; i < size; i++) {
			putInt(values[i]);
		}
	}

	public int[] getIntArray(final int nbOfInt) {
		final int[] result = new int[nbOfInt];
		for (int idxInt = 0; idxInt < nbOfInt; idxInt++) {
			result[idxInt] = getInt();
		}
		return result;
	}

	public void putLong(final long value) {
		put(value, Long.SIZE);
	}

	public long getLong() {
		return get(Long.SIZE);
	}

	public void putLongArray(final long[] values) {
		putLongArray(values, values.length);
	}

	public void putLongArray(final long[] values, final int size) {
		for (int i = 0; i < size; i++) {
			putLong(values[i]);
		}
	}

	public long[] getLongArray(final int nbOfLong) {
		final long[] result = new long[nbOfLong];
		for (int idxLong = 0; idxLong < nbOfLong; idxLong++) {
			result[idxLong] = getLong();
		}
		return result;
	}

	public void putFloat(final float value) {
		put(Float.floatToRawIntBits(value), Integer.SIZE);
	}

	public float getFloat() {
		return Float.intBitsToFloat((int) get(Integer.SIZE));
	}

	public void putFloatArray(final float[] values) {
		putFloatArray(values, values.length);
	}

	public void putFloatArray(final float[] values, final int size) {
		for (int i = 0; i < size; i++) {
			putFloat(values[i]);
		}
	}

	public float[] getFloatArray(final int nbOfFloat) {
		final float[] result = new float[nbOfFloat];
		for (int idxFloat = 0; idxFloat < nbOfFloat; idxFloat++) {
			result[idxFloat] = getFloat();
		}
		return result;
	}

	public void putDouble(final double value) {
		put(Double.doubleToRawLongBits(value), Long.SIZE);
	}

	public double getDouble() {
		return Double.longBitsToDouble(get(Long.SIZE));
	}

	public void putDoubleArray(final double[] values) {
		putDoubleArray(values, values.length);
	}

	public void putDoubleArray(final double[] values, final int size) {
		for (int idxDouble = 0; idxDouble < size; idxDouble++) {
			putDouble(values[idxDouble]);
		}
	}

	public double[] getDoubleArray(final int nbOfDouble) {
		final double[] result = new double[nbOfDouble];
		for (int idxDouble = 0; idxDouble < nbOfDouble; idxDouble++) {
			result[idxDouble] = getDouble();
		}
		return result;
	}

	public void putString(final String value) {
		this.putCharArray(value.toCharArray());
	}

	public void putString(final String value, final int size) {
		this.putCharArray(value.toCharArray(), size);
	}

	public void putStringArray(final String[] values) {
		putStringArray(values, values.length);
	}

	public void putStringArray(final String[] values, final int size) {
		for (int idxString = 0; idxString < size; idxString++) {
			putString(values[idxString]);
		}
	}

	public String getString(final int nbOfChar) {
		return new String(getCharArray(nbOfChar));
	}

	public long get(final int size) {
		// Check arguments
		if (size > Long.SIZE) {
			throw new IllegalArgumentException("Can't get more than " + Long.SIZE + "bits (arg is " + size + "bits)");
		}
		if (this.bitPosition + size > this.maxBitPosition) {
			throw new BufferOverflowException();
		}

		long result = 0;
		int remainingBitToRead = size;
		int nbOfBitToRead;

		// Loop while there is remaining bit(s) to read
		while (remainingBitToRead > 0) {

			// Compute number of bit(s) to read in this part of the buffer
			if (remainingBitToRead > BitUtils.BITS_PER_BYTE
					- MathUtils.modPowerOfTwo(this.bitPosition, BitUtils.BITS_PER_BYTE)) {
				nbOfBitToRead = BitUtils.BITS_PER_BYTE
						- MathUtils.modPowerOfTwo(this.bitPosition, BitUtils.BITS_PER_BYTE);
			} else {
				nbOfBitToRead = remainingBitToRead;
			}

			// Get bit(s) in this part of the buffer according to argument
			result |= (this.byteBuffer[this.bitPosition >> 3] & BitUtils.generateBitmask(
					MathUtils.modPowerOfTwo(this.bitPosition, BitUtils.BITS_PER_BYTE), nbOfBitToRead)) >> MathUtils
							.modPowerOfTwo(this.bitPosition, BitUtils.BITS_PER_BYTE) << size - remainingBitToRead;

			this.bitPosition += nbOfBitToRead;
			remainingBitToRead -= nbOfBitToRead;
		}

		if (this.bitPosition > this.maxBitPosition) {
			this.maxBitPosition = this.bitPosition;
		}

		return result;
	}
}
