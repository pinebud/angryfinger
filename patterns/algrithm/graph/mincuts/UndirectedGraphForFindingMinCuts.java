package graph.mincuts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * This program can only be used for undirected graphs
 * @author jingning
 *
 */
public class UndirectedGraphForFindingMinCuts {
	
	private Random randomV = new Random();
	
	/**
	 * String : vertex ID
	 */
	private Map<String,Vertex> verticesMap = new HashMap<String,Vertex>();
	
	private List<Vertex> verticesList = new ArrayList<Vertex>();
	
	public UndirectedGraphForFindingMinCuts(List<String> vertices){
		for(String str:vertices){
			Vertex  v = new Vertex(str);
			String id = v.getID();
			verticesList.add(v);
			verticesMap.put(id, v);
		}
	}
	
	public int findMinCuts(){
		int cnt =0;
		while(verticesList.size()>2){
			int pos = randomV.nextInt(verticesList.size());
			Vertex v1 = verticesList.get(pos);
			Vertex v2 = verticesMap.get(v1.chooseRandomAdjVertex());
			mergeVertex(v1, v2);
			verticesList.remove(pos);
		}
		Vertex a = verticesMap.get(verticesList.get(0).getID());
		Vertex b = verticesMap.get(verticesList.get(1).getID());
		int na = a.getAdjVertices().size();
		int nb = b.getAdjVertices().size();
		cnt = na<=nb? na:nb;
		return cnt;
	}
	
	/**
	 * O(n^2) because of Set.add ?
	 * @param a
	 * @param b
	 */
	private void mergeVertex(Vertex a, Vertex b){
		String aId = a.getID();
		String bId = b.getID();
		List<String> vList = new ArrayList<String>();
		List<String> aAdjVs = a.getAdjVertices();
		List<String> bAdjVs = b.getAdjVertices();
		for(String str:aAdjVs){
			if(!str.equals(bId)){
				vList.add(str);
				Vertex adjV = verticesMap.get(str);
				List<String> list = adjV.getAdjVertices();
				if(list.contains(aId)){
					list.remove(aId);
					list.add(bId);
				}
			}
		}
		for(String str:bAdjVs){
			if(!str.equals(aId)){
				vList.add(str);
			}
		}
		
		b.refreshAdjVertices(vList);
	}
	
	class Vertex implements Comparable<Vertex>{
		private String ID = null;
		private List<String> adjacentVerticesInID = new ArrayList<String>();	
		private Random randomE = new Random();
		
		/**
		 * The first element is ID of the vertex
		 * The following elements are the adjacent vertices
		 * @param verticesDelimitedBySpace
		 */
		public Vertex(String verticesDelimitedBySpace){
			String[] vertices = verticesDelimitedBySpace.split("\t");
			ID = vertices[0];
			int n = vertices.length;
			for(int i=1;i<n;i++){
				adjacentVerticesInID.add(vertices[i]);
			}
		}
		
		/**
		 * The first element is ID of the vertex
		 * The following elements are the adjacent vertices
		 * @param verticesDelimitedBySpace
		 */
		public Vertex(Set<String> verticesSet){
			Iterator<String> it = verticesSet.iterator();
			ID = it.next();
			while(it.hasNext()){
				adjacentVerticesInID.add(it.next());
			}
		}
		
		public String getID(){
			return ID;
		}
		
		public void refreshAdjVertices(List<String> list){
			adjacentVerticesInID.clear();
			Iterator<String> it = list.iterator();
			while(it.hasNext()){
				adjacentVerticesInID.add(it.next());
			}
		}
		
		public String chooseRandomAdjVertex(){
			int pos = randomE.nextInt(adjacentVerticesInID.size());
			return adjacentVerticesInID.get(pos);
		}
		
		public List<String> getAdjVertices(){
			return adjacentVerticesInID;
		}

		@Override
		public int compareTo(Vertex o) {
			return ID.compareTo(o.getID());
		}
		
	}

}
