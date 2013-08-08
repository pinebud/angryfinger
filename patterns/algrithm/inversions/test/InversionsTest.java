package inversions.test;

import java.io.File;

import inversions.Inversion;

import org.junit.Assert;
import org.junit.Test;

import others.Constants;
import util.FileUtility;

public class InversionsTest {
	
	private static Object[] intArray = null;
	static{
		intArray = FileUtility.readFileAsIntegerArray( new File(Constants.RESOURCE_FOLDER+"/inversions/IntegerArray.txt"));
	}

	@Test
	public void testNormalCase(){	
        long n = Inversion.findInversions(intArray);
		System.out.println(n);
	}
	
	@Test
	public void testSimpleCase(){
		long result = Inversion.findInversions(new Integer[]{5,1});
		Assert.assertEquals(1, result);
	}

	@Test
	public void testOddNumberOfInputArray(){
		long result =Inversion.findInversions(new Integer[]{5,10,1});
		Assert.assertEquals(2, result);
	}
	
	@Test
	public void testEvenNumberOfInputArray(){
		long result =Inversion.findInversions(new Integer[]{1,5,10,12,9,6});
		Assert.assertEquals(5, result);
	}	
	
	@Test
	public void testNoInversion(){
		long result =Inversion.findInversions(new Integer[]{1,5,7,9,10});
		Assert.assertEquals(0, result);
	}
	
	@Test
	public void testEmptyArray(){
		long result =Inversion.findInversions(new Integer[0]);
		Assert.assertEquals(0, result);
	}
}
