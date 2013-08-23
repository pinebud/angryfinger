package graph;

public class Edge {
	int weight = 0;
	Vertex source = null;
	Vertex target = null;
	public Edge(Vertex source, Vertex target){
		
	}
	
	public void setWeight(int weight){
		this.weight = weight;
	}
	
	public Vertex getSource(){
		return source;
	}
	
	public Vertex getTarget(){
		return target;
	}

}
