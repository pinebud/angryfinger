package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This data structure can be used for both undirected Graph and directed Graph
 * But it has redundant duplicated information which may consume too much memory for large volume
 * @author jingning
 *
 */
public class Graph {
	private Map<String,Vertex> vertexMap = new HashMap<String,Vertex>();
	
	private Map<String, Edge> edgeMap = new HashMap<String, Edge>();
	
	private List<Edge> edges = new ArrayList<Edge>();
	
	public Graph(List<String> rawNodeList){
		for(String rawNode:rawNodeList){
			String[] tempStrs = rawNode.split("\t");
			String vertexId = tempStrs[0];
			Vertex v = new Vertex(vertexId);
			vertexMap.put(vertexId, v);
			int len = tempStrs.length;
			for(int i=1;i<len;i++){
				String[] adjVertex_weight = tempStrs[i].trim().split(",");
				String adjVertexId = adjVertex_weight[0];
				String weightString = adjVertex_weight[1];
				int weight = Integer.valueOf(weightString);
				Vertex adjVertex = addVertex(adjVertexId, v);				
				Edge edge = new Edge(v,adjVertex);
				edge.setWeight(weight);
				edgeMap.put(vertexId+"-"+adjVertexId, edge);
				edges.add(edge);
			}
		}
	}
	
	private Vertex addVertex(String vertexId, Vertex adjVertex){
		Vertex v = vertexMap.get(vertexId);
		if(v==null){
			v = new Vertex(vertexId);
			v.AddAdjVertex(adjVertex);
			vertexMap.put(vertexId, v);
		}else{
			v.AddAdjVertex(adjVertex);
		}
		return v;
	}
	
	public Vertex getVertexById(String vertexId){
		return vertexMap.get(vertexId);
	}
	
	public Edge getEdge(Vertex source, Vertex target){
		return edgeMap.get(source.getId()+"-"+target.getId());
	}
	
	public List<Edge> getEdgeList(){
		return edges;
	}
	
}
