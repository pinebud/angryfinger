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
			addEdge(v, adjVertex,weight);
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
	
	public void addVertex(Vertex vertex){
		vertexMap.put(vertex.getId(), vertex);
		vertices.add(vertex);
	}
	public void addEdge(Edge edge){
		Vertex v1 = edge.getSource();
		Vertex v2 = edge.getTarget();
		edgeMap.put(v1.getId() + "-" + v2.getId(), edge);
		edgeMap.put(v2.getId() + "-" + v1.getId(), edge);
		edges.add(edge);
	}
	
	public void addEdge(Vertex v1, Vertex v2, int weight){
		v2.AddAdjVertex(v1);
		v1.AddAdjVertex(v2);
		Edge edge = new Edge(v1, v2);
		edge.setWeight(weight);
		edgeMap.put(v1.getId() + "-" + v2.getId(), edge);
		edgeMap.put(v2.getId() + "-" + v1.getId(), edge);
		edges.add(edge);
	}
	public void addVertices(List<Vertex> vertices){
		this.vertices.addAll(vertices);
		for(Vertex vertex: vertices){
			
		}
	}
	
	public void addEdges(List<Edge> edges){
		this.edges.addAll(edges);
		for(Edge edge: edges){
			
		}
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
	
	public List<Graph> generateSubIslandGraphs(){
		List<Graph> list = new ArrayList<Graph>();
		for(Vertex v: vertices){
			Graph g = new Graph();
			g.addVertex(v);
			list.add(g);
		}
		return list;
	}
	
	public Graph(){
		
	}
	
	public String toString(){
		StringBuilder str = new StringBuilder("[");
		for(Vertex v: vertices){
			str.append(v+",");
		}
		return str.substring(0, str.length()-1)+"]";
	}
}
