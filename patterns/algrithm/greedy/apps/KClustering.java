package greedy.apps;

import java.util.Collections;
import java.util.List;

import graph.Edge;
import graph.Graph;
import graph.GraphUnion;
import graph.Vertex;

/**
 * Greedy algorithm
 * @author jing.ning
 *
 */
public class KClustering {
	private int K = 4;
	
	private GraphUnion union = null;	
	
	private Graph rawGraph = null;
	
	public KClustering(List<String> rawList){
		rawGraph = new Graph(rawList);
		union = new GraphUnion(rawGraph);
	}
	
	public void setK(int k){
		this.K = k;
	}
	
	public int calculate(){
		List<Edge> edges = rawGraph.getEdgeList();
		Collections.sort(edges);
		int i = 0;
		while(union.getUnionSize()>K){
			Edge edge = edges.get(i++);
			Vertex v1 = edge.getSource();
			Vertex v2 = edge.getTarget();
			union.union(v1, v2, edge.getWeight());
		}
		
		int n = edges.size();
		for(;i<n;i++){
			Edge edge = edges.get(i);
			Vertex v1 = edge.getSource();
			Vertex v2 = edge.getTarget();
			Graph g1 = union.find(v1);
			Graph g2 = union.find(v2);
			if(g1!=g2)
				return edge.getWeight();
		}
		return 0;
	}
	
	
}
