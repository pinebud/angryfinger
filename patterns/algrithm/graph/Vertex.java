package graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
	private String id = null;
	
	private List<Vertex> adjcentVertices = new ArrayList<Vertex>();
	
	public Vertex(String id){
		this.id = id;
	}
	
	public void AddAdjVertex(Vertex v){
		adjcentVertices.add(v);
	}
	
	public String getId(){
		return id;
	}
}