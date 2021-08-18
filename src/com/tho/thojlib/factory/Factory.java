package com.tho.thojlib.factory;

import java.lang.reflect.Array;

public class Factory<T> {
	private final Class<? extends T> classOfT;

	public Factory(final Class<? extends T> classOfParam) {
		this.classOfT = classOfParam;
	}

	public T allocateNewInstance() {
		T allocatedObject = null;

		try {
			allocatedObject = this.classOfT.newInstance();
		} catch (final InstantiationException e) {
			e.printStackTrace();
		} catch (final IllegalAccessException e) {
			e.printStackTrace();
		}

		return allocatedObject;
	}

	public T[] allocate1DArray(final int size, final boolean allocateCells) {
		T[] array = null;

		array = (T[]) Array.newInstance(this.classOfT, size);

		if (allocateCells) {
			for (int i = 0; i < array.length; i++) {
				array[i] = allocateNewInstance();
			}
		}

		return array;
	}

	public T[][] allocate2DArray(final int size1, final int size2) {
		T[][] array = null;

		array = (T[][]) Array.newInstance(this.classOfT, size1, size2);
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = allocateNewInstance();
			}
		}

		return array;
	}

	public Object allocateArray(final int[] size) {
		Object array = null;
		array = Array.newInstance(this.classOfT, size);
		return array;
	}
}