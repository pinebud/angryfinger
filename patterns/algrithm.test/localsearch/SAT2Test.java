package localsearch;

import static org.junit.Assert.*;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;

import others.Constants;
import util.FileUtility;

public class SAT2Test {

	private static final String inputFile_quiz_prefix = Constants.RESOURCE_FOLDER
			+ "/localsearch/2sat";
	private static final String inputFile_quiz_postfix = ".txt";

	private static final File inputFile_test00 = new File(
			Constants.RESOURCE_FOLDER + "/localsearch/sat2_test00.txt");
	private static final File inputFile_test01 = new File(
			Constants.RESOURCE_FOLDER + "/localsearch/sat2_test01.txt");
	private static final File inputFile_test02 = new File(
			Constants.RESOURCE_FOLDER + "/localsearch/sat2_test02.txt");

	// private static final File inputFile_test03 = new
	// File(Constants.RESOURCE_FOLDER+"/localsearch/sat2_test03.txt");

	@Test
	public void testquiz() {
		for (int i = 1; i < 7; i++) {
			File inputFile_quiz = new File(inputFile_quiz_prefix + i
					+ inputFile_quiz_postfix);
			SAT2 instance = new SAT2(
					FileUtility.readFileByLineAsStringList(inputFile_quiz));
			for (int k = 0; k < 10; k++) {
				int rlt = instance.calculate();
				System.out.print(rlt);
			}
			System.out.println(",");
		}
	}

//	 @Test
	public void testSimple00() {
		SAT2 instance = new SAT2(
				FileUtility.readFileByLineAsStringList(inputFile_test00));
		int rlt = instance.calculate();
		Assert.assertEquals(1, rlt);
	}

//	 @Test
	public void testSimple01() {
		SAT2 instance = new SAT2(
				FileUtility.readFileByLineAsStringList(inputFile_test01));
		int rlt = instance.calculate();
		Assert.assertEquals(0, rlt);
	}

//	 @Test
	public void testSimple02() {
		SAT2 instance = new SAT2(
				FileUtility.readFileByLineAsStringList(inputFile_test02));
		int rlt = instance.calculate();
		Assert.assertEquals(1, rlt);
	}
}
