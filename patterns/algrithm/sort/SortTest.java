package sort;

import org.junit.Assert;
import org.junit.Test;

import factory.SorterFactory;

public class SortTest {
	
	@Test
	public void testEvenlySort(){
		testSort(new Integer[]{4,5,6,8,3,7,2,1});
	}
	
	@Test
	public void testOddlySort(){
		testSort(new Integer[]{4,5,6,8,3,7,2,9,1});
	}
	
	private void testSort(Integer[] rawArray){
		for(SortType sortType: SortType.values()){		
			ISort sorter = SorterFactory.getSorter(sortType);		
			long start_time = System.currentTimeMillis();
		    Integer[] sortedList = (Integer[]) sorter.sort(rawArray, SortOrder.asc);
		    long end_time = System.currentTimeMillis();
			long cost_time = end_time-start_time;
			System.out.println("The "+sortType.toString()+" sort cost "+cost_time+" millionseconds.");
			Assert.assertArrayEquals(new int[]{1,2,3,4,5,6,7,8}, toIntArray(sortedList) );
		}
	}
	
	private int[] toIntArray(Integer[] array){
		int[] newArray = new int[array.length];
		for(int i=0;i<array.length;i++){
			newArray[i]=array[i];
		}
		return newArray;
	}
}
