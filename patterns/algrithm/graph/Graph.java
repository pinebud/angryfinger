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
				String adjVertexId = adjVertex_weight[0];
				String weightString = adjVertex_weight[1];
				int weight = Integer.valueOf(weightString);
				Vertex adjVertex = addVertex(adjVertexId, v);				
				Edge edge = new Edge(v,adjVertex);
				edge.setWeight(weight);
				edges.add(edge);
			}
		}
	}
	
	private Vertex addVertex(String vertexId, Vertex adjVertex){
		Vertex v = vertices.get(vertexId);
		if(v==null){
			v = new Vertex(vertexId);
			v.AddAdjVertex(adjVertex);
			vertices.put(vertexId, v);
		}else{
			v.AddAdjVertex(adjVertex);
		}
		return v;
	}
	
	public Path calculateShortPath(String startVertexID, String endVertexID){
		Path p = new Path();
		return p;
	}
	
}
