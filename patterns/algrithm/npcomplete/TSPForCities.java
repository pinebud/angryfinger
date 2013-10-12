package npcomplete;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TSPForCities {
	private int N = 0;
	private Cordinate[] cordinates = null;
	private double[][] distances = null;
//	private Map<Integer,Cordinate> map = new HashMap<Integer, Cordinate>();
//	private Map<Cordinate,Integer> map = new HashMap<Cordinate,Integer>();
	
	public TSPForCities(List<String> lineList){
		N = Integer.valueOf(lineList.get(0).trim());
		cordinates = new Cordinate[N];
		distances = new double[N][N];
		for(int i = 1;i<=N;i++)
		{
			String[] strs = lineList.get(i).split(" ");
			double x = Double.valueOf(strs[0].trim());
			double y = Double.valueOf(strs[1].trim());
			Cordinate cordi = new Cordinate(x, y);
			cordi.setId(i-1);
			cordinates[i-1] = cordi;
//			map.put(i-1,cordi);
//			map.put(cordi, i-1);
			
		}
		for(int i=0;i<N;i++){	
			Cordinate a = cordinates[i];
			for(int j=0;j<=i;j++){
				if(j==i){
					distances[i][j] = 0;
					continue;
				}
				Cordinate b = cordinates[j];				
				double dist = Math.sqrt(Math.pow(a.x-b.x, 2)+Math.pow(a.y-b.y, 2));
				distances[i][j] = dist;
				distances[j][i] = dist;
			}
		}
	}
	
	public int calculate(){
		int rlt = -1;
		Cordinate[] array = Arrays.copyOf(cordinates, 2*N);
		Arrays.sort(array);
		tsp(array, 0 , N, 0);
		//TODO: Calculate result
		return rlt;
	}
	
	private void tsp(Cordinate[] array, int low, int high, int offset){
		int n = high - low+1;
		int mid = low+n/2;
		int newoffset = (offset+N)%(2*N);
		int current_low = offset+low, current_high = offset+high;
		if(n<3){
			//TODO: Base case
			for(int i=0;i<n;i++){
				array[newoffset+low+i] = array[current_low+i];
			}
		}
		int current_mid = offset+ mid;
		tsp(array, low, mid, newoffset);
		tsp(array, mid+1, high, newoffset);
		//TODO: Merge two sub-cycle.
		double min_distance = Double.MAX_VALUE;
		double len_of_two_cycle = 0.0; 
		for(int i=current_low;i<current_mid;i++){
			len_of_two_cycle+=distances[array[i].getId()][array[i+1].getId()];
		}
		len_of_two_cycle=+distances[array[current_mid].getId()][array[current_low].getId()];
		
		for(int i=current_mid+1;i<current_high;i++){
			len_of_two_cycle+=distances[array[i].getId()][array[i+1].getId()];
		}
		len_of_two_cycle=+distances[array[current_high].getId()][array[current_mid+1].getId()];
		
		for(int i=current_low;i<=current_mid;i++){
			int left1 = array[i].getId();
			int left2 = -1;
			if(i==offset+mid)
				left2 = array[current_low].getId();
			else
				left2= array[i+1].getId();
			for(int j=current_mid+1;j<=current_high;j++){
				int right1 = array[j].getId();
				int right2 = -1;
				if(j==current_high)
					right2 = array[current_mid+1].getId();
				else
					right2= array[j+1].getId();
				
				double removed_distance_sum = distances[left1][left2]+distances[right1][right2];
				double delta_distance1 = distances[left1][right1]+distances[left2][right2]-removed_distance_sum;
				double delta_distance2 = distances[left1][right2]+distances[left2][right1]-removed_distance_sum;
				double new_len = len_of_two_cycle-removed_distance_sum+(delta_distance1<delta_distance2?delta_distance1:delta_distance2);
				if(new_len<min_distance){
					min_distance = new_len;
					if(delta_distance1<delta_distance2){
						for(int k=low;k<=i-current_low;k++){
							array[newoffset+k]=array[offset+k];
						}
//						array[newoffset+k]+array[]
					}else{
						
					}					
				}
			}
		}
		
	}
	
	class Cordinate implements Comparable<Cordinate>{
		int id = -1;
		double x = 0;
		double y = 0;
		double radius = 0;
		public Cordinate(double cordi_x, double cordi_y){
			x = cordi_x;
			y = cordi_y;
			radius = Math.sqrt(Math.pow(x,2)+Math.pow(y, 2));
		}
		
		public void setId(int id){
			this.id = id;
		}
		
		public int getId(){
			return id;
		}
		
		public double getDistance(Cordinate a, Cordinate b){
			return Math.sqrt(Math.pow(a.x-b.x, 2)+Math.pow(a.y-b.y, 2));
		}

		@Override
		public int compareTo(Cordinate o) {
			if(radius<o.radius)
				return -1;
			else if(radius > o.radius)
				return 1;
			return 0;
		}
		
		public String toString(){
			return String.valueOf(id);
		}
	}
		
}
