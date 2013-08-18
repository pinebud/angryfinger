package datastructure.apps.test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import datastructure.apps.Probe2Sum;

public class Probe2SumTest {

	@Test
	public void test() {
		Integer[] inputArray = new Integer[]{1,-3,5,-2};
		int num = Probe2Sum.probe2Sum(inputArray, -2, 2);
		Assert.assertEquals(3, num);//1-3=-2,1-2=-1,-3+5=2
	}

}
