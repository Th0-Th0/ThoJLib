package com.tho.thojlib.pool;

import org.junit.Assert;
import org.junit.Test;

import com.tho.thojlib.factory.Factory;

public class PoolTest {

	public void testPool(final Pool<PoolTest> pool) {
		Assert.assertEquals(3, pool.getNbObjectsInPool());
		Assert.assertEquals(0, pool.getNbBorrowedObjects());

		PoolTest o1 = pool.borrow();
		Assert.assertNotNull(o1);
		Assert.assertEquals(2, pool.getNbObjectsInPool());
		Assert.assertEquals(1, pool.getNbBorrowedObjects());

		Assert.assertTrue(pool.release(o1));
		Assert.assertEquals(3, pool.getNbObjectsInPool());
		Assert.assertEquals(0, pool.getNbBorrowedObjects());

		Assert.assertFalse(pool.release(o1));
		Assert.assertEquals(3, pool.getNbObjectsInPool());
		Assert.assertEquals(0, pool.getNbBorrowedObjects());

		o1 = pool.borrow();
		Assert.assertNotNull(o1);
		Assert.assertEquals(2, pool.getNbObjectsInPool());
		Assert.assertEquals(1, pool.getNbBorrowedObjects());

		final PoolTest o2 = pool.borrow();
		Assert.assertNotNull(o2);
		Assert.assertEquals(1, pool.getNbObjectsInPool());
		Assert.assertEquals(2, pool.getNbBorrowedObjects());

		final PoolTest o3 = pool.borrow();
		Assert.assertNotNull(o3);
		Assert.assertEquals(0, pool.getNbObjectsInPool());
		Assert.assertEquals(3, pool.getNbBorrowedObjects());

		final PoolTest o4 = pool.borrow();
		Assert.assertNull(o4);
		Assert.assertEquals(0, pool.getNbObjectsInPool());
		Assert.assertEquals(3, pool.getNbBorrowedObjects());
	}

	@Test
	public void testPoolWithArrayList() {
		testPool(new PoolWithArrayList<>(new Factory<>(PoolTest.class), 3));
	}

	@Test
	public void testPoolWithBlockingQueue() {
		testPool(new PoolWithBlockingQueue<>(new Factory<>(PoolTest.class), 3, 500));
	}
}