package graph.apps;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import graph.*;

/**
 * Find Minimum Spanning Tree with Prim algorithm
 * 
 * @author jing.ning
 * 
 */
public class MSTPrim {
	private Graph g = null;

	private Set<Vertex> xSet = new HashSet<Vertex>();

	public MSTPrim(Graph graph) {
		this.g = graph;
	}

	public int calculateMST() {
		int numOfV = g.getVertexList().size();
		int sum = 0;
		Vertex s = g.getVertexList().get(0);
		s.setKnown(true);
		xSet.add(s);
		while (xSet.size()<numOfV) {
			int min = Integer.MAX_VALUE;
			Vertex xCursor = null;
			Vertex vCursor = null;
			for (Vertex x : xSet) {
				Set<Vertex> adjVertices = x.getAdjVertices();
				for (Vertex v : adjVertices) {
					if (v.isKnown())
						continue;
					int cost = g.getEdge(x, v).getWeight();
					if (cost < min) {
						min = cost;
						xCursor = x;
						vCursor = v;
					}
				}
			}
			//--------Write down the path------------
//			vCursor.setPv(xCursor);//Can't work for tracking the path
//			xCursor.setNv(vCursor);//Can't work for tracking the path
			g.getEdge(xCursor, vCursor).setExplored(true);
			//------------------------
			vCursor.setKnown(true);
			xSet.add(vCursor);
			sum += min;
		}
//		g.printForest();//can't work
		g.printPath();
		return sum;
	}
	
	
}
