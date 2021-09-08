package com.tho.thojlib.math.matrix;

public class Matrix {

	protected final double[][] matrix;

	public Matrix(final int nbRows, final int nbColumns) {
		this.matrix = new double[nbRows][nbColumns];
	}

	/**
	 * Fill all the matrix with the given value.
	 */
	public void fill(final double value) {
		for (int row = 0; row < this.matrix.length; row++) {
			for (int column = 0; column < this.matrix[0].length; column++) {
				set(row, column, value);
			}
		}
	}

	/**
	 * Set the (row;column) element with the given value.
	 */
	public void set(final int row, final int column, final double value) {
		this.matrix[row][column] = value;
	}

	/**
	 * @return The value of the (row;column) element.
	 */
	public double get(final int row, final int column) {
		return this.matrix[row][column];
	}

	/**
	 * @return The number of rows in this matrix.
	 */
	public int getNbRows() {
		return this.matrix.length;
	}

	/**
	 * @return The number of columns in this matrix.
	 */
	public int getNbColumns() {
		return this.matrix.length > 0 ? this.matrix[0].length : 0;
	}

	/**
	 * Transpose the matrix B into the matrix A.
	 */
	public static void transpose(final Matrix matA, final Matrix matB) {
		final Matrix matBToUse = matA == matB ? matB.clone() : matB;

		for (int row = 0; row < matA.matrix.length; row++) {
			for (int column = 0; column < matA.matrix[0].length; column++) {
				matA.set(row, column, matBToUse.matrix[column][row]);
			}
		}
	}

	/**
	 * Transpose the given matrix into this matrix.
	 */
	public void transpose(final Matrix matrixToTranspose) {
		Matrix.transpose(this, matrixToTranspose);
	}

	/**
	 * Transpose this matrix. It works only for a square matrix.
	 */
	public void transpose() {
		Matrix.transpose(this, this);
	}

	/**
	 * Calculate A = B * C.
	 */
	public static void product(final Matrix matA, final Matrix matB, final Matrix matC) {
		final Matrix matBToUse = matA == matB ? matB.clone() : matB;
		final Matrix matCToUse = matA == matC ? matC.clone() : matC;

		for (int row = 0; row < matA.matrix.length; row++) {
			for (int column = 0; column < matA.matrix[0].length; column++) {
				matA.set(row, column, 0.0);
				for (int i = 0; i < matBToUse.matrix[0].length; i++) {
					matA.set(row, column,
							matA.matrix[row][column] + matBToUse.matrix[row][i] * matCToUse.matrix[i][column]);
				}
			}
		}
	}

	/**
	 * Fill this matrix with the resulting matrix of A * B.
	 */
	public void product(final Matrix matA, final Matrix matB) {
		Matrix.product(this, matA, matB);
	}

	/**
	 * Fill this matrix with the resulting matrix of this * B.
	 */
	public void product(final Matrix matB) {
		Matrix.product(this, this, matB);
	}

	/**
	 * Calculate A = B * factor.
	 */
	public static void product(final Matrix matA, final Matrix matB, final double factor) {
		for (int row = 0; row < matA.matrix.length; row++) {
			for (int column = 0; column < matA.matrix[0].length; column++) {
				matA.set(row, column, factor * matB.matrix[row][column]);
			}
		}
	}

	/**
	 * Fill this matrix with the resulting matrix of B * factor.
	 */
	public void product(final Matrix matB, final double factor) {
		Matrix.product(this, matB, factor);
	}

	/**
	 * Fill this matrix with the resulting matrix of this * factor.
	 */
	public void product(final double factor) {
		Matrix.product(this, this, factor);
	}

	/**
	 * Calculate A = B + C.
	 */
	public static void sum(final Matrix matA, final Matrix matB, final Matrix matC) {
		for (int row = 0; row < matA.matrix.length; row++) {
			for (int column = 0; column < matA.matrix.length; column++) {
				matA.set(row, column, matB.matrix[row][column] + matC.matrix[row][column]);
			}
		}
	}

	/**
	 * Fill this matrix with the resulting matrix of A + B.
	 */
	public void sum(final Matrix matA, final Matrix matB) {
		Matrix.sum(this, matA, matB);
	}

	/**
	 * Fill this matrix with the resulting matrix of this + B.
	 */
	public void sum(final Matrix matB) {
		Matrix.sum(this, this, matB);
	}

	/**
	 * Calculate A = B + incr.
	 */
	public static void sum(final Matrix matA, final Matrix matB, final double incr) {
		for (int row = 0; row < matA.matrix.length; row++) {
			for (int column = 0; column < matA.matrix.length; column++) {
				matA.set(row, column, incr + matB.matrix[row][column]);
			}
		}
	}

	/**
	 * Fill this matrix with the resulting matrix of incr + B.
	 */
	public void sum(final Matrix matB, final double incr) {
		Matrix.sum(this, matB, incr);
	}

	/**
	 * Fill this matrix with the resulting matrix of this + incr.
	 */
	public void sum(final double incr) {
		Matrix.sum(this, this, incr);
	}

	/**
	 * Calculate A = B - C.
	 */
	public static void diff(final Matrix matA, final Matrix matB, final Matrix matC) {
		for (int row = 0; row < matA.matrix.length; row++) {
			for (int column = 0; column < matA.matrix.length; column++) {
				matA.set(row, column, matB.matrix[row][column] - matC.matrix[row][column]);
			}
		}
	}

	/**
	 * Fill this matrix with the resulting matrix of A - B.
	 */
	public void diff(final Matrix matA, final Matrix matB) {
		Matrix.diff(this, matA, matB);
	}

	/**
	 * Fill this matrix with the resulting matrix of this - B.
	 */
	public void diff(final Matrix matB) {
		Matrix.diff(this, this, matB);
	}

	/**
	 * Fill all the matrix with the given value.
	 */
	public void copy(final Matrix srcMatrix) {
		for (int row = 0; row < this.matrix.length; row++) {
			for (int column = 0; column < this.matrix[0].length; column++) {
				this.matrix[row][column] = srcMatrix.matrix[row][column];
			}
		}
	}

	/**
	 * Instantiate a new Matrix object with the same content as this Matrix.
	 */
	@Override
	public Matrix clone() {
		final Matrix clone = new Matrix(getNbRows(), getNbColumns());
		clone.copy(this);
		return clone;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder().append("[");
		for (int row = 0; row < this.matrix.length; row++) {
			if (row > 0) {
				sb.append("\n ");
			}
			sb.append("[");
			for (int column = 0; column < this.matrix[0].length; column++) {
				if (column > 0) {
					sb.append(", ");
				}
				sb.append(this.matrix[row][column]);
			}
			sb.append("]");
		}
		return sb.append("]").toString();
	}
}