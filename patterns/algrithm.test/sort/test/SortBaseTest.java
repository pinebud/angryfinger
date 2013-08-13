package sort.test;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import others.Constants;

import sort.ISort;
import sort.SortOrder;
import sort.SortType;
import sort.SortWithType;
import util.FileUtility;

import factory.DataFactory;
import factory.InvalidParsedArraySize;
import factory.SorterFactory;


@SortWithType(SortType.quicksort)
public class SortBaseTest {
	
	protected SortType[] sortTypes = null;
	
	protected File TEST_RESULT_FILE = Constants.TEST_RESULT_FILE;
	
	@Test 
	public void testSortedArray(){
		for (SortType sortType : sortTypes) {
			Assert.assertArrayEquals(
					new int[] { 1, 2, 3, 4, 5, 6, 7, 8 },
					testSort(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8 }, sortType, SortOrder.asc));
		}
	}
	
	@Test
	public void testReversedSortedArray() {	
		for (SortType sortType : sortTypes) {
			Assert.assertArrayEquals(
					new int[] {1, 2, 3, 4, 5, 6, 7, 8},
					testSort(new Integer[] { 8,7,6,5,4,3,2,1 }, sortType, SortOrder.asc));
		}
	}
	
	@Test 
	public void testDupValueElements(){
		for (SortType sortType : sortTypes) {
			Assert.assertArrayEquals(
					new int[] { 1, 2, 2, 5, 5, 6, 7, 7 },
					testSort(new Integer[] { 5, 5, 6, 7, 2, 7, 2, 1 }, sortType, SortOrder.asc));
		}
	}
	
	@Test
	public void testEvenlySort() {	
		for (SortType sortType : sortTypes) {
			Assert.assertArrayEquals(
					new int[] { 1, 2, 3, 4, 5, 6, 7, 8 },
					testSort(new Integer[] { 4, 5, 6, 8, 3, 7, 2, 1 }, sortType, SortOrder.asc));
		}
	}

	@Test
	public void testOddlySort() {
		for (SortType sortType : sortTypes) {
			Assert.assertArrayEquals(
					new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
					testSort(new Integer[] { 4, 5, 6, 8, 3, 7, 2, 9, 1 },
							sortType, SortOrder.asc));
		}
	}
	
	@Test
	public void testDesendSort(){
		for (SortType sortType : sortTypes) {
			Assert.assertArrayEquals(
					new int[] { 9,8,7,6,5,4,3,2,1},
					testSort(new Integer[] { 4, 5, 6, 8, 3, 7, 2, 9, 1 },
							sortType, SortOrder.desc));
		}
	}
	
	/**
	 * Performance Test
	 */
//	@Test
	public void perfTestForBestCase(){
		Integer[] unsortedBoxedIntArray = DataFactory.generateReversedSortedIntArray();
		for(int i = 0;i<10;i++){
			for (SortType sortType : sortTypes) {
				int[] output = testSort(unsortedBoxedIntArray, sortType, SortOrder.desc);
				FileUtility.appendLine(TEST_RESULT_FILE, DataFactory.serialize(output));
				
			}
		}
	}
	
	/**
	 * Performance Test
	 */	
//	@Test
	public void perfTestForWorstCase(){
		Integer[] unsortedBoxedIntArray = DataFactory.generateReversedSortedIntArray();
		for(int i = 0;i<10;i++){
			for (SortType sortType : sortTypes) {
				int[] output = testSort(unsortedBoxedIntArray, sortType, SortOrder.asc);
				FileUtility.appendLine(TEST_RESULT_FILE, DataFactory.serialize(output));
				
			}
		}
	}
	
	/**
	 * Performance test
	 * @throws InvalidParsedArraySize
	 */
//	@Test
	public void perfTestForSort() throws InvalidParsedArraySize{
		int[] unsortedArray = DataFactory.unserialize(FileUtility.readFileAsString(DataFactory.TEST_DATA_FILE).trim());
		Integer[] unsortedBoxedIntArray = boxIntArray(unsortedArray);
		for(int i = 0;i<10;i++){
			for (SortType sortType : sortTypes) {
				int[] output = testSort(unsortedBoxedIntArray, sortType, SortOrder.asc);
				FileUtility.appendLine(TEST_RESULT_FILE, DataFactory.serialize(output));
			}
		}
	}
	
	@Before
	public void setup(){
		sortTypes = getClass().getAnnotation(SortWithType.class).value();
	}

	protected int[] testSort(Object[] rawArray, SortType sortType, SortOrder sortOrder) {
		ISort sorter = SorterFactory.getSorter(sortType);
		long start_time = System.currentTimeMillis();
		Object[] sortedList = sorter.sort(rawArray, sortOrder);
		long end_time = System.currentTimeMillis();
		long cost_time = end_time - start_time;
		System.out.println("The " + sortType.toString() + " sort cost "
				+ cost_time + " millionseconds.");
		return toIntArray(sortedList);
	}

	private int[] toIntArray(Object[] array) {
		int[] newArray = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = (Integer) array[i];
		}
		return newArray;
	}
	
	private Integer[] boxIntArray(int[] array) {
		Integer[] boxedArray = new Integer[array.length];
		for (int i = 0; i < array.length; i++) {
			boxedArray[i] = (Integer) array[i];
		}
		return boxedArray;
	}
}
