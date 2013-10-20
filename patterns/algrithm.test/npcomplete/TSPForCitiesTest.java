package npcomplete;

import java.io.File;
import java.util.List;
import java.util.Random;

import junit.framework.Assert;

import org.junit.Test;

import others.Constants;
import util.FileUtility;

public class TSPForCitiesTest {
	private static final File inputFile_quiz = new File(Constants.RESOURCE_FOLDER+"/npcomplete/tsp.txt");
	private static final File inputFile_test00 = new File(Constants.RESOURCE_FOLDER+"/npcomplete/tsp_test00.txt");
	private static final File inputFile_test01 = new File(Constants.RESOURCE_FOLDER+"/npcomplete/tsp_test01.txt");
	private static final File inputFile_test02 = new File(Constants.RESOURCE_FOLDER+"/npcomplete/tsp_test02.txt");
//	@Test
	public void testSqrt(){
		int rlt = (int)( Math.sqrt(6.25)+1.2);
		Assert.assertEquals(3, rlt);
	}

//	@Test
	public void testRandResult(){
		for(int i=0;i<10;i++){
			Random rand = new Random();
			int randInt = rand.nextInt(3);
			System.out.println(randInt-1);
		}
	}	

	@Test
	public void quiz(){
		List<String> lineList = FileUtility.readFileByLineAsStringList(inputFile_quiz);
		TSPForCities instance = new TSPForCities(lineList);
		double rlt = instance.calculate(10000);
		Assert.assertEquals(26442, (int)rlt);
	}

	
	@Test
	public void testSimple0(){
		List<String> lineList = FileUtility.readFileByLineAsStringList(inputFile_test00);
		TSPForCities instance = new TSPForCities(lineList);
		double rlt = instance.calculate(100);
		int expected =(int)( Math.sqrt(10.25)+Math.sqrt(9.5)+7.5+6);
		Assert.assertEquals(expected, (int)rlt);
	}

	@Test
	public void testSimple1(){
		List<String> lineList = FileUtility.readFileByLineAsStringList(inputFile_test01);
		TSPForCities instance = new TSPForCities(lineList);
		double rlt = instance.calculate(100);
		Assert.assertEquals(37, (int)rlt);
	}
	
	@Test
	public void testSimple2(){
		List<String> lineList = FileUtility.readFileByLineAsStringList(inputFile_test02);
		TSPForCities instance = new TSPForCities(lineList);
		double rlt = instance.calculate(100);
		Assert.assertEquals(73, (int)rlt);
	}
}
