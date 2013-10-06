package dynamicprogramming.apps;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import others.Constants;
import util.FileUtility;

public class KnapsackTest {
	private static final File inputFile_test00 = new File(Constants.RESOURCE_FOLDER+"/dynamic/knapsacktest00.txt");
	private static final File inputFile_test01 = new File(Constants.RESOURCE_FOLDER+"/dynamic/knapsacktest01.txt");
	private static final File inputFile_quiz = new File(Constants.RESOURCE_FOLDER+"/dynamic/knapsack1.txt");
	private static final File inputFile_quiz2 = new File(Constants.RESOURCE_FOLDER+"/dynamic/knapsack_big.txt");
	@Test
	public void test00() {
		List<String> lineList = FileUtility.readFileByLineAsStringList(inputFile_test00);
		Knapsack knapsack = new Knapsack(lineList);
		int rlt = knapsack.calculate();
		Assert.assertEquals(14, rlt);
	}
	
	@Test
	public void test01() {
		List<String> lineList = FileUtility.readFileByLineAsStringList(inputFile_test01);
		Knapsack knapsack = new Knapsack(lineList);
		int rlt = knapsack.calculate();
		Assert.assertEquals(8, rlt);
	}
	
//	@Test
	public void quiz() {
		List<String> lineList = FileUtility.readFileByLineAsStringList(inputFile_quiz);
		Knapsack knapsack = new Knapsack(lineList);
		int rlt = knapsack.calculate();
		System.out.println(rlt);
	}
	
	@Test
	public void quiz2() {
		List<String> lineList = FileUtility.readFileByLineAsStringList(inputFile_quiz2);
		Knapsack knapsack = new Knapsack(lineList);
		int rlt = knapsack.calculate();
		System.out.println(rlt);
	}
}
