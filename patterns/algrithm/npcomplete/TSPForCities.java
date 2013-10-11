package npcomplete;

import java.util.Arrays;
import java.util.List;

public class TSPForCities {
	private int N = 0;
	private Cordinate[] cordinates = null;
	
	public TSPForCities(List<String> lineList){
		N = Integer.valueOf(lineList.get(0).trim());
		cordinates = new Cordinate[N];
		for(int i = 1;i<=N;i++)
		{
			String[] strs = lineList.get(i).split(" ");
			double x = Double.valueOf(strs[0].trim());
			double y = Double.valueOf(strs[1].trim());
			Cordinate cordi = new Cordinate(x, y);
			cordinates[i-1] = cordi;
		}
		Arrays.sort(cordinates);
	}
	
	public int calculate(){
		int rlt = -1;
		tsp(cordinates, 0 , N);
		//TODO: Calculate result
		return rlt;
	}
	
	private void tsp(Cordinate[] cordinates, int low, int high){
		int n = high - low;
		if(n<3){
			//TODO: Base case
		}
		int mid = low + n/2;
		tsp(cordinates, low, mid);
		tsp(cordinates, mid+1, high);
		//TODO: Merge two sub-cycle.
	}
	
	class Cordinate implements Comparable<Cordinate>{
		double x = 0;
		double y = 0;
		public Cordinate(double cordi_x, double cordi_y){
			x = cordi_x;
			y = cordi_y;
		}
		
		public double getDistance(Cordinate a, Cordinate b){
			return Math.sqrt(Math.pow(a.x-b.x, 2)+Math.pow(a.y-b.y, 2));
		}

		@Override
		public int compareTo(Cordinate o) {
			//TODO: 
			return 0;
		}
	}
	
	
}
