package search.test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import search.BinarySearch;

public class BinarySearchTest {

	@Test
	public void testOdd() {
		int[] inputArray = new int[]{-6,0,100};
		Assert.assertEquals(-1, BinarySearch.find(inputArray, -9));
		Assert.assertEquals(0, BinarySearch.find(inputArray, -6));
		Assert.assertEquals(-1, BinarySearch.find(inputArray, -2));
		Assert.assertEquals(1, BinarySearch.find(inputArray, 0));
		Assert.assertEquals(-1, BinarySearch.find(inputArray, 17));
		Assert.assertEquals(inputArray.length-1, BinarySearch.find(inputArray, 100));
		Assert.assertEquals(-1, BinarySearch.find(inputArray, 200));
	}
	
	@Test
	public void testEven() {
		int[] inputArray = new int[]{-6,0};
		Assert.assertEquals(-1, BinarySearch.find(inputArray, -9));
		Assert.assertEquals(0, BinarySearch.find(inputArray, -6));
		Assert.assertEquals(-1, BinarySearch.find(inputArray, -1));
		Assert.assertEquals(1, BinarySearch.find(inputArray, 0));
		Assert.assertEquals(-1, BinarySearch.find(inputArray, 200));
	}
	
	@Test
	public void testSingleElement() {
		int[] inputArray = new int[]{0};
		Assert.assertEquals(-1, BinarySearch.find(inputArray, -9));
		Assert.assertEquals(0, BinarySearch.find(inputArray, 0));
		Assert.assertEquals(-1, BinarySearch.find(inputArray, 10));
	}
	
	@Test
	public void testDup(){
		int[] inputArray = new int[]{-6,1,5,5,9};
		Assert.assertEquals(2, BinarySearch.find(inputArray, 5));
//		Assert.assertEquals(3, BinarySearch.find(inputArray, 5));
	}
	
	@Test
	public void testEmptyInputArray(){
		
	}
	
	@Test
	public void testForMinAndMaxInteger(){
		
	}

	@Test
	public void testRandomInput(){
		
	}
	
	@Test
	public void testLargeVolume(){
		
	}
	
}
