package graph;

import java.util.ArrayList;
import java.util.List;

public class Path {
	private List<Edge> edges = new ArrayList<Edge>();
	
	private int distance = 0;
	
	public List<Edge> getEdges(){
		return edges;
	}
	
	public int getDistance(){
		return distance;
	}
	
	public void addEdgeToPath(Edge e){
		edges.add(e);
	}
}
