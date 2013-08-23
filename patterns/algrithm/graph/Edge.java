package graph;

public class Edge {
	IWeight weight = null;
	Vertex source = null;
	Vertex target = null;
	public Edge(Vertex source, Vertex target){
		
	}
	
	public void setWeight(IWeight weight){
		this.weight = weight;
	}
	
	public Vertex getSource(){
		return source;
	}
	
	public Vertex getTarget(){
		return target;
	}

}
