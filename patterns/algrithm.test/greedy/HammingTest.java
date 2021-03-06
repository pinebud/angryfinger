package greedy;

import greedy.apps.Hamming;

import java.io.File;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import others.Constants;
import util.FileUtility;

public class HammingTest {
	private static final File inputFile_test00 = new File(Constants.RESOURCE_FOLDER+"/graph/clustering/hammingtest00.txt");
	private static final File inputFile_test01 = new File(Constants.RESOURCE_FOLDER+"/graph/clustering/hammingtest01.txt");
	private static final File inputFile_test02 = new File(Constants.RESOURCE_FOLDER+"/graph/clustering/hammingtest02.txt");
	private static final File inputFile_test03 = new File(Constants.RESOURCE_FOLDER+"/graph/clustering/hammingtest03.txt");
	private static final File inputFile_test04 = new File(Constants.RESOURCE_FOLDER+"/graph/clustering/hammingtest04.txt");
	private static final File inputFile_quiz = new File(Constants.RESOURCE_FOLDER+"/graph/clustering/clustering_big.txt");
//	private static final File inputFile_quiz = new File(Constants.RESOURCE_FOLDER+"/graph/clustering/cluster_big_100000_32.txt");
	@Test
	public void test00() {
		List<String> lineList = FileUtility.readFileByLineAsStringList(inputFile_test00);
		Hamming hamming = new Hamming(lineList);
		int rlt = hamming.calculate();
		Assert.assertEquals(1, rlt);
	}
	
	@Test
	public void test01_dup() {
		List<String> lineList = FileUtility.readFileByLineAsStringList(inputFile_test01);
		Hamming hamming = new Hamming(lineList);
		int rlt = hamming.calculate();
		Assert.assertEquals(4, rlt);
	}
	
	@Test
	public void test02() {
		List<String> lineList = FileUtility.readFileByLineAsStringList(inputFile_test02);
		Hamming hamming = new Hamming(lineList);
		int rlt = hamming.calculate();
		Assert.assertEquals(45, rlt);
	}

	@Test
	public void test03() {
		List<String> lineList = FileUtility.readFileByLineAsStringList(inputFile_test03);
		Hamming hamming = new Hamming(lineList);
		int rlt0 = hamming.calculate();
		int rlt1 = hamming.calculate();
		int rlt = hamming.calculate();
		Assert.assertEquals(29, rlt);
	}
	
	@Test
	public void test04_nj() {
		List<String> lineList = FileUtility.readFileByLineAsStringList(inputFile_test04);
		Hamming hamming = new Hamming(lineList);
		int rlt0 = hamming.calculate();
		int rlt = hamming.calculate();
		Assert.assertEquals(2, rlt);
	}
	
	@Test
	public void quiz() {
		List<String> lineList = FileUtility.readFileByLineAsStringList(inputFile_quiz);
		Hamming hamming = new Hamming(lineList);
		int rlt0 = hamming.calculate();
		System.out.println(rlt0);
		int rlt1 = hamming.calculate();
		System.out.println(rlt1);
		int rlt2 = hamming.calculate();
		System.out.println(rlt2);
		int rlt = hamming.calculate();
		System.out.println("rlt = "+rlt);
//		System.out.println("rlt = "+rlt);
	}

}
