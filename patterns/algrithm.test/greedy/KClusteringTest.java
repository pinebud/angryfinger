package greedy;

import static org.junit.Assert.*;

import graph.Graph;
import greedy.apps.KClustering;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import others.Constants;
import util.FileUtility;

public class KClusteringTest {
	
	private static final File inputFile = new File(Constants.RESOURCE_FOLDER+"/graph/clustering/clustering1.txt");
	private static final File test1_inputFile = new File(Constants.RESOURCE_FOLDER+"/graph/clustering/clustering1_test1.txt");
	private static final File test2_inputFile = new File(Constants.RESOURCE_FOLDER+"/graph/clustering/clustering1_test2.txt");
	

	@Test
	public void testSimple_0() {
		List<String> rawNodeList = new ArrayList<String>();
		rawNodeList.add("1 2 1");
		rawNodeList.add("1 3 4");
		rawNodeList.add("2 3 2");
		rawNodeList.add("2 4 6");
		rawNodeList.add("3 4 3");
		
		KClustering kClustering = new KClustering(rawNodeList);
		kClustering.setK(2);
		Assert.assertEquals(3,kClustering.calculate());
	}
	
	@Test
	public void testSimple_1(){
		List<String> lineList = FileUtility.readFileByLineAsStringList(test1_inputFile,1);
		KClustering kClustering = new KClustering(lineList);
		kClustering.setK(4);
		Assert.assertEquals(7,kClustering.calculate());
		
	}
	@Test
	public void testSimple_2(){
		List<String> lineList = FileUtility.readFileByLineAsStringList(test2_inputFile,1);
		KClustering kClustering = new KClustering(lineList);
		kClustering.setK(4);
		Assert.assertEquals(8,kClustering.calculate());		
	}
	
	@Test
	public void quiz(){
		List<String> lineList = FileUtility.readFileByLineAsStringList(inputFile,1);
		KClustering kClustering = new KClustering(lineList);
		kClustering.setK(4);
		System.out.println(kClustering.calculate());		
	}
}
