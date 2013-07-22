package sort;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import factory.SorterFactory;

@SortWithType(SortType.merge)
public class SortTest {
	
	private SortType[] sortTypes = null;

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
}
