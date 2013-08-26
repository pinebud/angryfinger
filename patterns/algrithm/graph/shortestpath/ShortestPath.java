package graph.shortestpath;

import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import graph.*;

public class ShortestPath {
	
	private Graph g = null;
	
	private PriorityQueue<Vertex> vDistHeap = null;
	
	public ShortestPath(Graph graph){
		g = graph;
		vDistHeap = new PriorityQueue<Vertex>(g.getVertexList().size(), new VertexComparator());
	}

	/*
	 * O(n*m*(n+logn))=O(n^2*m)?
	 * The first n is about areAllVerticesKnown(), m is about for(adjVertices), logn is the heap add operation
	 */
	public void calculateShortestPath(String startVertexID){
		Vertex startV = g.getVertexById(startVertexID);
		startV.setDist(0);
		vDistHeap.add(startV);
//		init();
		while(!areAllVerticesKnown()){
			Vertex minDistV = vDistHeap.poll();
			minDistV.setKnown(true);
			List<Vertex> adjVertices = minDistV.getAdjVertices();
			for(Vertex adjV: adjVertices){
				int newDist = minDistV.getDist()+g.getEdge(minDistV, adjV).getWeight();
				adjV.setDist(min(adjV.getDist(), newDist));
				adjV.setPv(minDistV);
				if(!vDistHeap.contains(adjV)&&!g.getEdge(minDistV, adjV).isExplored()){ //contains may cost O(n)?
					g.getEdge(adjV, minDistV).setExplored(true);
					g.getEdge(minDistV,adjV).setExplored(true);					
					vDistHeap.add(adjV);
				}
			}
		}
	}
	
	public int getShortestDistOf(String vertexId){
		return g.getVertexById(vertexId).getDist();
	}
	
	private void init(){
		Iterator<Vertex> it = g.getVertexList().iterator();
		while(it.hasNext()){
			vDistHeap.add(it.next());
		}
	}
	
	/*
	 * O(n)
	 */
	private boolean areAllVerticesKnown(){
		Iterator<Vertex> it = g.getVertexList().iterator();
		while(it.hasNext()){
			if(!it.next().isKnown()){
				return false;
			}
		}
		return true;
	}
	
	private int min(int a, int b){
		return a-b<=0?a:b;
	}
}
