package sort;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import util.FileUtility;

import factory.DataFactory;
import factory.InvalidParsedArraySize;
import factory.SorterFactory;

@SortWithType(SortType.merge)
public class SortTest {
	
	private static final File TEST_RESULT_FILE=new File(System.getProperty("user.dir")+"/testResult.txt");
	
	private SortType[] sortTypes = null;


	@Test
	public void perfTestForSort() throws InvalidParsedArraySize{
		int[] unsortedArray = DataFactory.unserialize(FileUtility.readFileAsString(DataFactory.TEST_DATA_FILE).trim());
		Integer[] unsortedBoxedIntArray = boxIntArray(unsortedArray);
		for(int i = 0;i<10;i++){
			for (SortType sortType : sortTypes) {
				int[] output = testSort(unsortedBoxedIntArray, sortType, SortOrder.asc);
				FileUtility.append(TEST_RESULT_FILE, DataFactory.serialize(output));
			}
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
	
	@Before
	public void setup(){
		sortTypes = getClass().getAnnotation(SortWithType.class).value();
	}

	private int[] testSort(Integer[] rawArray, SortType sortType, SortOrder sortOrder) {
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
