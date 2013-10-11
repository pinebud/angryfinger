package npcomplete;

import java.io.File;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import others.Constants;
import util.FileUtility;

public class TSPForCitiesTest {
	private static final File inputFile_test00 = new File(Constants.RESOURCE_FOLDER+"/npcomplete/tsp_test00.txt");
	
	@Test
	public void testSqrt(){
		int rlt = (int)( Math.sqrt(6.25)+1.2);
		Assert.assertEquals(3, rlt);
	}

//	@Test
	public void testSimple(){
		List<String> lineList = FileUtility.readFileByLineAsStringList(inputFile_test00);
		TSPForCities instance = new TSPForCities(lineList);
		int rlt = instance.calculate();
		int expected =(int)( Math.sqrt(10.25)+Math.sqrt(9.5)+7.5);
		Assert.assertEquals(expected, rlt);
	}

}
