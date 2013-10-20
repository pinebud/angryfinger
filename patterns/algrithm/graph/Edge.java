package graph;

public class Edge implements Comparable<Edge>{
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
	
	@Override
	public String toString(){
		return source.getId()+"->"+target.getId()+": "+weight;
	}

	@Override
	public int compareTo(Edge o) {
		if(this.weight<o.getWeight())
			return -1;
		else if(weight>o.getWeight())
			return 1;
		return 0;
	}
	
}
