package datastructure.apps.test;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;

import others.Constants;
import util.FileUtility;
import datastructure.apps.Probe2Sum;

@SuppressWarnings("deprecation")
public class Probe2SumTest {
	private static final File inputFile = new File(Constants.RESOURCE_FOLDER+"/graph/algo1-programming_prob-2sum.txt");

	@Test
	public void test() {
		Long[] inputArray = new Long[]{(long) 1,(long) -3,(long) 5,(long) -2};
		int lowerbound = -2, upperbound=2;
		int num = Probe2Sum.probe2Sum(inputArray, lowerbound, upperbound);
		Assert.assertEquals(3, num);//1-3=-2,1-2=-1,-3+5=2
	}
	
	@Test
	public void testLargeVolume(){
		Long[] inputArray = FileUtility.readFileAsLongArray(inputFile);
		int num = Probe2Sum.probe2Sum(inputArray, 0, 20001);
		System.out.println(num);
	}

}
