package dynamicprogramming.apps;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import others.Constants;
import util.FileUtility;

public class AllPairShortestPathTest {
	private static final File inputFile_g1 = new File(Constants.RESOURCE_FOLDER+"/dynamic/g1.txt");
	private static final File inputFile_g2 = new File(Constants.RESOURCE_FOLDER+"/dynamic/g2.txt");
	private static final File inputFile_g3 = new File(Constants.RESOURCE_FOLDER+"/dynamic/g3.txt");
	private static final File inputFile_t0 = new File(Constants.RESOURCE_FOLDER+"/dynamic/apsp_test00.txt");
	private static final File inputFile_t1 = new File(Constants.RESOURCE_FOLDER+"/dynamic/apsp_test01.txt");
	private static final File inputFile_t2 = new File(Constants.RESOURCE_FOLDER+"/dynamic/apsp_test02.txt");
	private static final File inputFile_t3 = new File(Constants.RESOURCE_FOLDER+"/dynamic/apsp_test03.txt");
	private static final File inputFile_t4 = new File(Constants.RESOURCE_FOLDER+"/dynamic/apsp_test04.txt");
	
	@Test
	public void testSimplePath() {
		List<String> lineList = FileUtility.readFileByLineAsStringList(inputFile_t0);
		AllPairShortestPath instance = new AllPairShortestPath(lineList);
		int rlt = instance.floydwarshall();
		Assert.assertEquals(-3, rlt);
	}	
	
	@Test
	public void testNegativeCycle(){
		List<String> lineList = FileUtility.readFileByLineAsStringList(inputFile_t1);
		AllPairShortestPath instance = new AllPairShortestPath(lineList);
		int rlt = instance.floydwarshall();
		Assert.assertEquals(Integer.MIN_VALUE, rlt);
	}
	
	@Test
	public void testNegativeShortestPath(){
		List<String> lineList = FileUtility.readFileByLineAsStringList(inputFile_t2);
		AllPairShortestPath instance = new AllPairShortestPath(lineList);
		int rlt = instance.floydwarshall();
		Assert.assertEquals(-3, rlt);
	}
	
	@Test
	public void testPathWithNonNegativeEdges(){
		List<String> lineList = FileUtility.readFileByLineAsStringList(inputFile_t3);
		AllPairShortestPath instance = new AllPairShortestPath(lineList);
		int rlt = instance.floydwarshall();
		Assert.assertEquals(3, rlt);
	}
	
	@Test
	public void testNonNegativeCycle(){
		List<String> lineList = FileUtility.readFileByLineAsStringList(inputFile_t4);
		AllPairShortestPath instance = new AllPairShortestPath(lineList);
		int rlt = instance.floydwarshall();
		Assert.assertEquals(-7, rlt);
	}
	
	@Test
	public void quiz(){
		List<String> lineList = FileUtility.readFileByLineAsStringList(inputFile_g1);
		AllPairShortestPath instance = new AllPairShortestPath(lineList);
		int rlt = instance.floydwarshall();
		System.out.println(rlt);
		
		List<String> lineList1 = FileUtility.readFileByLineAsStringList(inputFile_g2);
		AllPairShortestPath instance1 = new AllPairShortestPath(lineList1);
		int rlt1 = instance1.floydwarshall();
		System.out.println(rlt1);
		
		List<String> lineList2 = FileUtility.readFileByLineAsStringList(inputFile_g3);
		AllPairShortestPath instance2 = new AllPairShortestPath(lineList2);
		int rlt2 = instance2.floydwarshall();
		System.out.println(rlt2);
	}

}
