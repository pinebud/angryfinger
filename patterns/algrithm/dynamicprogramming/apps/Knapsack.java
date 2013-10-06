package dynamicprogramming.apps;

import java.util.List;

public class Knapsack {
	private int knapsack = 0;
	private int[] weightArray = null;
	private int[] valueArray = null;
	private int[] sArray = null;
	
	public Knapsack(List<String> rawList){
		String[] pair = rawList.get(0).split(" ");
		knapsack = Integer.valueOf(pair[0].trim());
		int n = Integer.valueOf(pair[1].trim());
		valueArray = new int[n];
		weightArray = new int[n];
		for(int i=0;i<n;i++){
			String line = rawList.get(i+1);
			String[] value_weight = line.split(" ");
			valueArray[i] = Integer.valueOf(value_weight[0]);
			weightArray[i] = Integer.valueOf(value_weight[1]);
			
		}
		sArray = new int[knapsack+1];
	}
	
	public int calculate(){
		int n = weightArray.length;
		for(int i = 0;i<n-1;i++){
			int value = valueArray[i];
			int weight = weightArray[i];
			for(int j=knapsack;j>=weight;j--){			
				int deltaw = j-weight;
				sArray[j] = max(sArray[j], sArray[deltaw]+value);
			}
		}
		sArray[knapsack] = max(sArray[knapsack],sArray[knapsack-weightArray[n-1]]+valueArray[n-1]);
		return sArray[knapsack];
	}
	
//	public int calculate(){
//		int n = weightArray.length;
//		return func(n,knapsack);
//	}
//	
//	private int func(int n, int w){
//		if(n==0){
//			sArray[0]
//			return weightArray[0]>w?0:valueArray[0];
//		}
//		max(func(n-1, w), func(n-1, w-weightArray[n])+valueArray[n]);
//		sArray[w] = max(sArray[w], sArray[w-weightArray[n]]+valueArray[n]);
//		return sArray[w];
//	}
	
	
//	public int calculate(){
//		int n = weightArray.length;
//		int[][] A = new int[n+1][knapsack+1];  
//		for(int j=0;j<=knapsack;j++){
//			A[0][j]=0;
//		}
//		for(int i = 1; i<=n;i++){
//			int value= valueArray[i-1];
//			int weight= weightArray[i-1];
//			for(int j=0;j<=knapsack;j++){
//				int w = j-weight;
//				A[i][j] = max(A[i-1][j], w<0?0:A[i-1][w]+value);
//			}
//		}
//		return A[n][knapsack];
//	}
	
	private int max(int a, int b){
		return a>=b?a:b;
	}
}
