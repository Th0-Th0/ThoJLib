package com.tho.thojlib.bit;

public class IntStack {

	private final int[] buffer;
	private int position;

	public IntStack(final int size) {
		this.position = 0;
		this.buffer = new int[size];
	}

	/**
	 * Push value into the stack.
	 */
	public void push(final int value) {
		try {
			this.buffer[this.position++] = value;
		} catch (final ArrayIndexOutOfBoundsException e) {
			this.position--;
			throw e;
		}
	}

	/**
	 * Pop value from the stack.
	 */
	public int pop() {
		try {
			return this.buffer[--this.position];
		} catch (final ArrayIndexOutOfBoundsException e) {
			this.position++;
			throw e;
		}
	}

	/**
	 * Clear stack.
	 */
	public void clear() {
		this.position = 0;
	}
}
