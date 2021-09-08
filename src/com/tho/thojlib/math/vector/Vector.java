package com.tho.thojlib.math.vector;

import com.tho.thojlib.math.matrix.Matrix;

public class Vector extends Matrix {

	public Vector(final int dimension) {
		this(dimension, true);
	}

	public Vector(final int dimension, final boolean isColumnVector) {
		super(isColumnVector ? dimension : 1, isColumnVector ? 1 : dimension);
	}

	/**
	 * Compute and return the norm of this vector.
	 */
	public double norm() {
		double norm = 0;
		for (int row = 0; row < this.matrix.length; row++) {
			for (int column = 0; column < this.matrix[0].length; column++) {
				norm += this.matrix[row][column] * this.matrix[row][column];
			}
		}
		return Math.sqrt(norm);
	}

	public int getDimension() {
		return getNbRows() != 1 ? getNbRows() : getNbColumns();
	}

	@Override
	public Vector clone() {
		final Vector clone = new Vector(getDimension(), getNbColumns() == 1);
		clone.copy(this);
		return clone;
	}
}