package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import util.readers.GraphReader;

/**
 * This data structure can be used only for undirected Graph.
 * It has redundant duplicated information which may consume too much memory
 * for large volume becase of the two extra maps and two extra lists.
 * 
 * @author jingning
 * 
 */
public class Graph {
	private Map<String, Vertex> vertexMap = new HashMap<String, Vertex>();

	private Map<String, Edge> edgeMap = new HashMap<String, Edge>();

	private List<Vertex> vertices = new ArrayList<Vertex>();

	private List<Edge> edges = new ArrayList<Edge>();

//	public Graph(List<String> rawNodeList) {
//		for (String rawNode : rawNodeList) {
//			String[] tempStrs = rawNode.split("\t");
//			String vertexId = tempStrs[0];
//			Vertex v = addVertex(vertexId);
//			int len = tempStrs.length;
//			for (int i = 1; i < len; i++) {
//				String[] adjVertex_weight = tempStrs[i].trim().split(",");
//				String adjVertexId = adjVertex_weight[0];
//				String weightString = adjVertex_weight[1];
//				int weight = Integer.valueOf(weightString);
//				Vertex adjVertex = addVertex(adjVertexId);
//				adjVertex.AddAdjVertex(v);
//				Edge edge = new Edge(v, adjVertex);
//				edge.setWeight(weight);
//				edgeMap.put(vertexId + "-" + adjVertexId, edge);
//				edges.add(edge);
//			}
//		}
//	}
	
	public Graph(List<String> rawList){
		this(rawList, null);
	}

	public Graph(List<String> rawList, GraphReader reader) {	
		List<String> newList = rawList;
		if(reader!=null)
			newList = reader.read(rawList);
		for (String rawNode : newList) {
			String[] tempStrs = rawNode.split(" ");
			String vertexId = tempStrs[0];
			String adjVertexId = tempStrs[1];
			String weightString = tempStrs[2];
			int weight = Integer.valueOf(weightString);
			Vertex v = addVertex(vertexId);
			Vertex adjVertex = addVertex(adjVertexId);
			adjVertex.AddAdjVertex(v);
			v.AddAdjVertex(adjVertex);
			Edge edge = new Edge(v, adjVertex);
			edge.setWeight(weight);
			edgeMap.put(vertexId + "-" + adjVertexId, edge);
			edgeMap.put(adjVertexId + "-" + vertexId, edge);
			edges.add(edge);
		}
	}

	private Vertex addVertex(String vertexId) {
		Vertex v = vertexMap.get(vertexId);
		if (v == null) {
			v = new Vertex(vertexId);
			vertexMap.put(vertexId, v);
			vertices.add(v);
		}
		return v;
	}

	public Vertex getVertexById(String vertexId) {
		return vertexMap.get(vertexId);
	}

	public Edge getEdge(Vertex source, Vertex target) {
		return edgeMap.get(source.getId() + "-" + target.getId());
	}

	public List<Edge> getEdgeList() {
		return edges;
	}

	public List<Vertex> getVertexList() {
		return vertices;
	}
	
	/*
	 * O(n)
	 */
	public boolean areAllVerticesKnown(){
		Iterator<Vertex> it = getVertexList().iterator();
		while(it.hasNext()){
			if(!it.next().isKnown()){
				return false;
			}
		}
		return true;
	}
	
	public void printForest(){
		for(Vertex v: getVertexList()){
			if(v.getPv()==null)				
				printTree(v);
		}
	}
	
	public void printTree(Vertex startV){
		StringBuilder str = new StringBuilder();
		Vertex nV = startV.getNv();
		while(nV !=null){
			nV = nV.getNv();
		}
		System.out.println(str.toString());
	}
	
	
	public void printPath(){
		for(Edge e: getEdgeList()){
			if(e.isExplored())				
				System.out.println(e.toString());
		}
	}
}
