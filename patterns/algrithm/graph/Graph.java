package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Directed Graph
 * @author jingning
 *
 */
public class Graph {
	private Map<String,Vertex> vertices = new HashMap<String,Vertex>();
	
	private List<Edge> edges = new ArrayList<Edge>();
	
	public Graph(List<String> rawNodeList){
		for(String rawNode:rawNodeList){
			String[] tempStrs = rawNode.split("\t");
			String vertexId = tempStrs[0];
			Vertex v = new Vertex(vertexId);
			vertices.put(vertexId, v);
			int len = tempStrs.length;
			for(int i=1;i<len;i++){
				String[] adjVertex_weight = tempStrs[i].trim().split(",");
				
			}
		}
	}
	
	public Path calculateShortPath(String startVertexID, String endVertexID){
		Path p = new Path();
		return p;
	}
	
}
