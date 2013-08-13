package sort.test;

import java.io.File;

import org.junit.Test;

import factory.DataFactory;

import others.Constants;
import sort.SortOrder;
import sort.SortType;
import sort.SortWithType;
import util.FileUtility;

@SortWithType(SortType.quicksort)
public class QuickSortTest extends SortBaseTest{

	private static Object[] intArray = null;
	static{
		intArray = FileUtility.readFileAsIntegerArray( new File(Constants.RESOURCE_FOLDER+"/sort/QuickSort.txt"));
	}
	
	@Test
	public void test10k(){
		for (SortType sortType : sortTypes) {
			int[] output = testSort(intArray, sortType, SortOrder.asc);
			FileUtility.appendLine(TEST_RESULT_FILE, DataFactory.serialize(output));
		}
	}
}
