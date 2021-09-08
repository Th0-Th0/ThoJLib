package com.tho.thojlib.math.matrix;

public class MatrixBuilder {

	private MatrixBuilder() {
		// Utility class
	}

	/**
	 * Instantiate a square matrix.
	 */
	public static Matrix square(final int order) {
		return new Matrix(order, order);
	}

	/**
	 * Instantiate an identity matrix.
	 */
	public static Matrix identity(final int order) {
		final Matrix identity = new Matrix(order, order);
		for (int i = 0; i < order; i++) {
			identity.set(i, i, 1.0);
		}
		return identity;
	}

	/**
	 * Instantiate a new Matrix object as a transposed matrix of the given src
	 * matrix.
	 */
	public static Matrix transpose(final Matrix srcMatrix) {
		final Matrix transposedMatrix = new Matrix(srcMatrix.getNbColumns(), srcMatrix.getNbRows());
		transposedMatrix.transpose(srcMatrix);
		return transposedMatrix;
	}

	/**
	 * Instantiate a new Matrix object filled with the resulting matrix of A * B.
	 */
	public static Matrix product(final Matrix matA, final Matrix matB) {
		final Matrix resultingMatrix = new Matrix(matA.getNbRows(), matB.getNbColumns());
		resultingMatrix.product(matA, matB);
		return resultingMatrix;
	}

	/**
	 * Instantiate a new Matrix object filled with the resulting matrix of A *
	 * factor.
	 */
	public static Matrix product(final Matrix matA, final double factor) {
		final Matrix resultingMatrix = new Matrix(matA.getNbRows(), matA.getNbColumns());
		resultingMatrix.product(matA, factor);
		return resultingMatrix;
	}

	/**
	 * Instantiate a new Matrix object filled with the resulting matrix of A + B.
	 */
	public static Matrix sum(final Matrix matA, final Matrix matB) {
		final Matrix resultingMatrix = new Matrix(matA.getNbRows(), matA.getNbColumns());
		resultingMatrix.sum(matA, matB);
		return resultingMatrix;
	}

	/**
	 * Instantiate a new Matrix object filled with the resulting matrix of A + incr.
	 */
	public static Matrix sum(final Matrix matA, final double incr) {
		final Matrix resultingMatrix = new Matrix(matA.getNbRows(), matA.getNbColumns());
		resultingMatrix.sum(matA, incr);
		return resultingMatrix;
	}

	/**
	 * Instantiate a new Matrix object filled with the resulting matrix of A - decr.
	 */
	public static Matrix diff(final Matrix matA, final Matrix matB) {
		final Matrix resultingMatrix = new Matrix(matA.getNbRows(), matA.getNbColumns());
		resultingMatrix.diff(matA, matB);
		return resultingMatrix;
	}
}
