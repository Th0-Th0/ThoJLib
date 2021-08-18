package com.tho.thojlib.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.tho.thojlib.factory.Factory;

public class PoolWithBlockingQueue<T> implements Pool<T> {

	private final BlockingQueue<T> pool;
	private final long timeoutMs;
	private final Factory<T> factory;

	public PoolWithBlockingQueue(final Factory<T> factory, final int size, final long blockingTimeoutMs) {
		this.factory = factory;
		this.pool = new LinkedBlockingQueue<>(size);
		this.timeoutMs = blockingTimeoutMs;
		for (int i = 0; i < size; i++) {
			this.pool.add(this.factory.allocateNewInstance());
		}
	}

	@Override
	public T borrow() {
		T borrowedObject = null;
		try {
			borrowedObject = this.pool.poll(this.timeoutMs, TimeUnit.MILLISECONDS);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		return borrowedObject;
	}

	@Override
	public T peek() {
		return this.pool.peek();
	}

	@Override
	public boolean release(final T objectToRelease) {
		boolean isReleased = false;
		if (this.pool.contains(objectToRelease)) {
			// Double release attempt
		} else if (objectToRelease != null) {
			try {
				isReleased = this.pool.offer(objectToRelease, this.timeoutMs, TimeUnit.MILLISECONDS);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
		return isReleased;
	}

	@Override
	public int getNbBorrowedObjects() {
		return this.pool.remainingCapacity();
	}

	@Override
	public int getNbObjectsInPool() {
		return this.pool.size();
	}
}