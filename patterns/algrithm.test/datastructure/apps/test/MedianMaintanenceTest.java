package datastructure.apps.test;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;

import others.Constants;
import util.FileUtility;

import datastructure.apps.MedianMaintanence;

public class MedianMaintanenceTest {
	private static final File inputFile = new File(Constants.RESOURCE_FOLDER+"/graph/Median.txt");

	@Test
	public void testRegularCase_0_odd() {
		MedianMaintanence instance = new MedianMaintanence(new Integer[]{1,7,2,8,5});
		int rlt = instance.func(); //1+1+2+2+5=11
		Assert.assertEquals(11, rlt);
	}
	
	@Test
	public void testRegularCase_1_even() {
		MedianMaintanence instance = new MedianMaintanence(new Integer[]{1,7,2,8,5,11});
		int rlt = instance.func(); //1+1+2+2+5+5=16
		Assert.assertEquals(16, rlt);
	}
	
	@Test
	public void testRegularCase_2_dup() {
		MedianMaintanence instance = new MedianMaintanence(new Integer[]{2,7,7,11,2,2});
		int rlt = instance.func(); //2+2+7+7+7+2=27
		Assert.assertEquals(27, rlt);
	}
	
	@Test
	public void testBoundaryCase_0(){
		MedianMaintanence instance = new MedianMaintanence(new Integer[]{});
		int rlt = instance.func(); //1+1+2+2+5=11
		Assert.assertEquals(-1, rlt);
		System.out.println("Can't calculate the sum of median because the input array has no elements.");
	}
	
	@Test
	public void testBoundaryCase_1(){
		MedianMaintanence instance = new MedianMaintanence(new Integer[]{99999});
		int rlt = instance.func(); //1+1+2+2+5=11
		Assert.assertEquals(9999, rlt);
	}
	
	@Test
	public void test10k(){
		Integer[] inputArray = FileUtility.readFileAsIntegerArray(inputFile);
		MedianMaintanence instance = new MedianMaintanence(inputArray);
		int rlt = instance.func(); //1+1+2+2+5=11
		System.out.println(rlt);
	}

}
