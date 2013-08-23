package gragh.test;

import static org.junit.Assert.*;

import graph.Graph;
import graph.Path;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class ShortestPathTest {

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
		Path p = g.calculateShortPath("1", "4");
		Assert.assertEquals(6, p.getDistance());
	}

}
