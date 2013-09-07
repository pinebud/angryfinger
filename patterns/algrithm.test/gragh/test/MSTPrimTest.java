package gragh.test;

import graph.Graph;
import graph.apps.MSTPrim;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import others.Constants;
import util.FileUtility;

public class MSTPrimTest {
	private static final File inputFile = new File(Constants.RESOURCE_FOLDER+"/graph/edges.txt");

	@Test
	public void testSmoke() {
		List<String> rawNodeList = new ArrayList<String>();
		rawNodeList.add("1 2 1");
		rawNodeList.add("1 3 -4");
		rawNodeList.add("2 4 6");
		rawNodeList.add("2 3 2");
		rawNodeList.add("3 4 3");
		
		Graph g = new Graph(rawNodeList);
		MSTPrim prim = new MSTPrim(g);
		int mstCost = prim.calculateMST();
		Assert.assertEquals(0, mstCost);
	}
	
	@Test
	public void quiz(){
		List<String> rawNodeList = FileUtility.readFileByLineAsStringList(inputFile);
		rawNodeList.remove(0);
		Graph g = new Graph(rawNodeList);
		MSTPrim prim = new MSTPrim(g);
		int mstCost = prim.calculateMST();
		System.out.println(mstCost);
	}
  
    @Test
    public void testDup(){
   }
   
    @Test
    public void testRandomInput(){
          
   }
   
    @Test
    public void testLargeVolume(){
          
   }


}
