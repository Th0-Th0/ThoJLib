package com.tho.thojlib.pool;

public interface Pool<T> {

	public abstract T borrow();

	public abstract T peek();

	public abstract boolean release(final T objectToRelease);

	public abstract int getNbBorrowedObjects();

	public abstract int getNbObjectsInPool();
}
