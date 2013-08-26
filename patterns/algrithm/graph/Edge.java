package graph;

public class Edge {
	int weight = 0;
	Vertex source = null;
	Vertex target = null;
	boolean explored = false;
	public Edge(Vertex source, Vertex target){
		this.source=source;
		this.target = target;
	}
	
	public void setWeight(int weight){
		this.weight = weight;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public Vertex getSource(){
		return source;
	}
	
	public Vertex getTarget(){
		return target;
	}

	public boolean isExplored() {
		return explored;
	}

	public void setExplored(boolean explored) {
		this.explored = explored;
	}

	
}
