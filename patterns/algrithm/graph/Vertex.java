package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Vertex implements Comparable<Vertex> {
	private String id = null;
	
	private boolean known = false;
	
	private int dist = Integer.MAX_VALUE;
	
	private Set<Vertex> adjcentVertices = new HashSet<Vertex>();
	
	private Vertex pv = null;
	
	private Vertex nv = null;
	
	public Vertex(String id){
		this.id = id;
	}
	
	public void AddAdjVertex(Vertex v){
		adjcentVertices.add(v);
	}
	
	public Set<Vertex> getAdjVertices(){
		return adjcentVertices;
	}
	
	public String getId(){
		return id;
	}

	public boolean isKnown() {
		return known;
	}

	public void setKnown(boolean known) {
		this.known = known;
	}

	public int getDist() {
		return dist;
	}

	public void setDist(int dist) {
		this.dist = dist;
	}

	public Vertex getPv() {
		return pv;
	}

	public void setPv(Vertex pv) {
		this.pv = pv;
	}
	
	public Vertex getNv() {
		return nv;
	}

	public void setNv(Vertex nv) {
		this.nv = nv;
	}

	@Override
	public int compareTo(Vertex o) {
		if(dist<o.getDist())
			return -1;
		if(dist>o.getDist())
			return 1;
		return 0;
	}
	
	public String toString(){
		return id;
	}
	
}