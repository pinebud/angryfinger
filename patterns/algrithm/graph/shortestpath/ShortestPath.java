package graph.shortestpath;

import graph.*;

public class ShortestPath {
	
	private Graph g = null;

	public ShortestPath(Graph graph){
		g = graph;
	}

	public Path calculateShortestPath(String startVertexID, String endVertexID){
		Path p = new Path();
		Vertex startV = g.getVertexById(startVertexID);
		Vertex endV = g.getVertexById(endVertexID);
		
		
		return p;
	}
}
