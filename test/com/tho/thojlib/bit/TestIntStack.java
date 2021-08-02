package com.tho.thojlib.bit;

import org.junit.Assert;
import org.junit.Test;

public class TestIntStack {

	@Test
	public void testStack() {
		final IntStack stack = new IntStack(3);

		stack.push(11);
		stack.push(22);
		stack.push(33);
		try {
			stack.push(44);
			Assert.fail();
		} catch (final ArrayIndexOutOfBoundsException e) {
			// Exception expected
		}

		stack.clear();

		stack.push(11);
		stack.push(22);
		stack.push(33);
		try {
			stack.push(44);
			Assert.fail();
		} catch (final ArrayIndexOutOfBoundsException e) {
			// Exception expected
		}

		Assert.assertEquals(33, stack.pop());
		Assert.assertEquals(22, stack.pop());
		Assert.assertEquals(11, stack.pop());
		try {
			stack.pop();
			Assert.fail();
		} catch (final ArrayIndexOutOfBoundsException e) {
			// Exception expected
		}
	}
}
