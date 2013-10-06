package dynamicprogramming.apps;

import java.util.List;

public class AllPairShortestPath {
	
	private int N = 0;
	
	private int[][] d = null;
	
	private int[][] p = null; // This is for construct the shortest i-j path.
	
	public AllPairShortestPath(List<String> rawList){
		 String[] vNum_eNum = rawList.get(0).split(" ");
		 N = Integer.valueOf(vNum_eNum[0]);
		 int numOfEdges = Integer.valueOf(vNum_eNum[1]);
		 d = new int[N][N];
		 
		 //O(N^2)
		 for(int i=0;i<N;i++){
			 for(int j=0;j<N;j++){
				 if(i==j){
					 d[i][j] = 0;
				 }else{
					 d[i][j]=Integer.MAX_VALUE;
				 }
			 }
		 }
		 
		 //O(M)
		 for(int i=1;i<=numOfEdges;i++){
			 String[] xyw = rawList.get(i).split(" ");
			 int x = Integer.valueOf(xyw[0]);
			 int y = Integer.valueOf(xyw[1]);
			 int w = Integer.valueOf(xyw[2]);
			 d[x-1][y-1] = w;
		 }
	}
	
	public int floydwarshall(){
		int min = Integer.MAX_VALUE;
		
		for(int k=0;k<N;k++){
			for(int i=0;i<N;i++){
				 for(int j=0;j<N;j++){
					 int a = d[i][k];
					 if(a==Integer.MAX_VALUE)
						 continue;
					 int b = d[k][j];
					 if(b==Integer.MAX_VALUE)
						 continue;
					 int newd = d[i][k]+d[k][j];// What if it overflow?
					 if(i==j&&newd<0){
						 System.err.println("There's negative cycles in the given graph! We can't get the shortest path.");
						 return Integer.MIN_VALUE;
					 }
					 if(newd <d[i][j]){
						 d[i][j] = newd;
					 }
				 }
			}
		}
		
		for(int i=0;i<N;i++){
			 for(int j=0;j<N;j++){
				 if(i!=j&&d[i][j]<min){
					 min = d[i][j];
				 }
			 }
		 }
		
		return min;
	}
	
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("[ ");
		for(int i=0;i<N;i++){
			str.append("[ ");
			 for(int j=0;j<N-1;j++){
				 str.append(d[i][j]+",");
			 }
			 str.append(d[i][N-1]);
			 str.append(" ],");
		}
		str.setCharAt(str.length()-1, ']');
		return str.toString();
	}

}
