package com.tho.thojlib.factory;

import org.junit.Assert;
import org.junit.Test;

public class FactoryTest {

	protected static int constructorFlag;

	public FactoryTest() {
		FactoryTest.constructorFlag++;
	}

	@Test
	public void testObjectAllocation() {
		System.out.println("\n\nTest factory object allocation");
		FactoryTest.constructorFlag = 0;

		final Factory<FactoryTest> factory = new Factory<>(FactoryTest.class);

		for (int i = 1; i < 100; i++) {
			final FactoryTest o = factory.allocateNewInstance();
			Assert.assertNotNull(o);
		}
	}

	@Test
	public void test1DArrayAllocation() {
		System.out.println("\n\nTest factory array allocation");
		FactoryTest.constructorFlag = 0;
		final int arraySize = 10;

		final Factory<FactoryTest> factory = new Factory<>(FactoryTest.class);
		final FactoryTest[] array = factory.allocate1DArray(arraySize, true);

		Assert.assertNotNull(array);
		Assert.assertEquals(arraySize, array.length);
		for (int i = 0; i < array.length; i++) {
			Assert.assertNotNull(array[i]);
		}
	}

	@Test
	public void test2DArrayAllocation() {
		System.out.println("\n\nTest factory 2D allocation");
		FactoryTest.constructorFlag = 0;
		final int arraySize1 = 10;
		final int arraySize2 = 20;

		final Factory<FactoryTest> factory = new Factory<>(FactoryTest.class);
		final FactoryTest[][] array = factory.allocate2DArray(arraySize1, arraySize2);

		Assert.assertNotNull(array);
		Assert.assertEquals(arraySize1, array.length);
		Assert.assertEquals(arraySize2, array[0].length);

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				Assert.assertNotNull(array[i][j]);
			}
		}
	}

	@Test
	public void testGenericArrayAllocation() {
		System.out.println("\n\nTest factory generic allocation");
		FactoryTest.constructorFlag = 0;
		final int[] arraySize = new int[] { 1, 2, 3, 4 };

		final Factory<FactoryTest> factory = new Factory<>(FactoryTest.class);
		final Object o = factory.allocateArray(arraySize);

		Assert.assertTrue(o instanceof FactoryTest[][][][]);
		final FactoryTest[][][][] array = (FactoryTest[][][][]) o;
		Assert.assertNotNull(array);
		Assert.assertEquals(arraySize[0], array.length);
		Assert.assertEquals(arraySize[1], array[0].length);
		Assert.assertEquals(arraySize[2], array[0][0].length);
		Assert.assertEquals(arraySize[3], array[0][0][0].length);
	}
}