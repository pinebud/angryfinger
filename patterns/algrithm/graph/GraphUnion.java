package graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import util.readers.GraphReader;

import datastructure.IUnion;

public class GraphUnion{
	List<Graph> subGraphlist = null;
	
	public GraphUnion(Graph graph){
		subGraphlist = graph.generateSubIslandGraphs();
	}
	
	public boolean union(Vertex v1, Vertex v2, int weight){
		if(v1==null||v2==null){
			System.err.println("Can't union NULL!");
			return false;
		}
		Graph g1 = find(v1);
		Graph g2 = find(v2);
		if(g1==g2){
//			System.out.println("The two vertices are in the same graph, no union is needed.");
			return false;
		}
		List<Vertex> vertices = g2.getVertexList();
		int len = vertices.size();
		for(int index =0;index<len;index++){
			Vertex vertex = vertices.get(index);
			g1.addVertex(vertex);
		}
		
		List<Edge> edges = g2.getEdgeList();
		len = edges.size();
		for(int i=0;i<len;i++){
			g1.addEdge(edges.get(i));
		}
		g1.addEdge(v1,v2, weight);		
		subGraphlist.remove(g2);
		return true;
	}
	 
	
	public Graph find(Vertex vertex){
		for(Graph gu: subGraphlist){
			Vertex v = gu.getVertexById(vertex.getId());
			if(v!=null) return gu;
		}
		return null;
	}
	
	public int getUnionSize(){
		return subGraphlist.size();
	}

}

class GraphUnit implements IUnion<Graph> {
	List<Graph> list = new LinkedList<Graph>();

	public GraphUnit(Graph graph){
		list.add(graph);
	}
	
	public void add(Graph graph){
		list.add(graph);
	}
	
	public void add(GraphUnit u){
		list.addAll(u.getElements());
	}
	
	@Override
	public List<Graph> getElements(){
		return list;
	}

	@Override
	public void union(IUnion<Graph> unit) {
		this.list.addAll(unit.getElements());
	}

	@Override
	public boolean has(Graph graph, Comparator<Graph> t) {
		for(Graph g:list){
			if(t.compare(g, graph)==0)
				return true;
		}
		return false;
	}
	
	
}
