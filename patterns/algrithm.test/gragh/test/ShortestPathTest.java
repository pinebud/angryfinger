package gragh.test;

import static org.junit.Assert.*;

import graph.Graph;
import graph.shortestpath.ShortestPath;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import others.Constants;
import util.FileUtility;

public class ShortestPathTest {
	
	private static final File inputFile = new File(Constants.RESOURCE_FOLDER+"/graph/dijkstraData.txt");

	@Test
	public void test() {
		/*
		 * Undirected graph
		 */
		List<String> rawNodeList = new ArrayList<String>();
		rawNodeList.add("1	2,1	3,4");
		rawNodeList.add("2	1,1	3,2	4,6");
		rawNodeList.add("3	1,4	2,2	4,3");
		rawNodeList.add("4	2,6	3,3");
		
		Graph g = new Graph(rawNodeList);
		ShortestPath sp = new ShortestPath(g);
		sp.calculateShortestPath("1");
		
		String[] targetVertices= new String[]{
				"1","2","3","4"
		};		
		
		int[] dists = new int[]{
				0,1,3,6
		};
		
		int len = targetVertices.length;
		for(int i=0;i<len;i++){			
			String targetV = targetVertices[i];
			int expectedDist = dists[i];
			Assert.assertEquals(expectedDist, sp.getShortestDistOf(targetV));
		}
	}
	
//	@Test
	public void quiz(){
		List<String> rawNodeList = FileUtility.readFileByLineAsStringList(inputFile);
		Graph g = new Graph(rawNodeList);
		ShortestPath sp = new ShortestPath(g);
		sp.calculateShortestPath("1");
		
		String[] targetVertices= new String[]{
				"7","37","59","82","99","115","133","165","188","197"
		};
		
		int len = targetVertices.length;
		for(int i=0;i<len;i++){			
			String targetV = targetVertices[i];
			System.out.println("The shortest distance from Vertex 1 to Vertex "+ targetV+" is " + sp.getShortestDistOf(targetV));
//			System.out.print(sp.getShortestDistOf(targetV)+",");
		}
	}

}
