package com.tho.thojlib.pool;

import java.util.ArrayList;

import com.tho.thojlib.factory.Factory;

public class PoolWithArrayList<T> implements Pool<T> {

	private final ArrayList<T> pool;
	private final Factory<T> factory;
	private final int capacity;

	public PoolWithArrayList(final Factory<T> factory, final int initialSize) {
		this.factory = factory;
		this.pool = new ArrayList<>(initialSize);
		for (int i = 0; i < initialSize; i++) {
			this.pool.add(this.factory.allocateNewInstance());
		}
		this.capacity = initialSize;
	}

	@Override
	public T borrow() {
		T borrowedObject = null;
		if (this.pool.size() != 0) {
			borrowedObject = this.pool.remove(this.pool.size() - 1);
		}
		return borrowedObject;
	}

	@Override
	public T peek() {
		return this.pool.get(this.pool.size() - 1);
	}

	@Override
	public boolean release(final T objectToRelease) {
		boolean isReleased = false;
		if (this.pool.contains(objectToRelease)) {
			// Double release attempt
		} else if (this.pool.size() >= this.capacity) {
			// Pool is full
		} else if (objectToRelease != null) {
			isReleased = this.pool.add(objectToRelease);
		}
		return isReleased;
	}

	@Override
	public int getNbBorrowedObjects() {
		return this.capacity - this.pool.size();
	}

	@Override
	public int getNbObjectsInPool() {
		return this.pool.size();
	}
}