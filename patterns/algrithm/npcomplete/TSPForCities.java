package npcomplete;

import java.nio.charset.CodingErrorAction;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

public class TSPForCities {
	private int N = 0;
	private Cordinate[] cordinates = null;
	private double[][] distances = null;
	
	
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
	
	public double calculate(){
		Cordinate[] array = Arrays.copyOf(cordinates, N);
		mysort(array);
		List<Cordinate> list = new LinkedList<Cordinate>();
		list.add(array[0]);
		double tpsLength = 0;
		for(int i=1;i<N;i++){
			Cordinate w = array[i];
			int size = list.size();
			double minDelta=Double.MAX_VALUE;
			int insertAfter = 0;
			for(int j=0;j<size;j++){
				Cordinate u = list.get(j);
				Cordinate v = list.get((j+1)%size);
				double dist_u_v = distances[u.getId()][v.getId()];
				double dist_u_w = distances[u.getId()][w.getId()];
				double dist_w_v = distances[w.getId()][v.getId()];
				double delta =dist_u_w + dist_w_v - dist_u_v;
				if(delta<minDelta){
					minDelta = delta;
					insertAfter = j+1;
				}
			}
			list.add(insertAfter, w);
			tpsLength+=minDelta;
		}
		if(tpsLength<18){
			System.out.println(list.toString());
			double sum = distances[list.get(list.size()-1).getId()][list.get(0).getId()];
			for(int i=0;i<list.size()-1;i++){
				double dist = distances[list.get(i).getId()][list.get(i+1).getId()];
				sum+=dist;
			}
			System.out.println(sum);
		}
		return tpsLength;
	}
	
	public double calculate(int tryingCount){
		double min = Double.MAX_VALUE;
		for(int i=0;i<tryingCount;i++){
			double rlt = calculate();
//			System.out.println(rlt);
			if(rlt<min)
				min = rlt;
		}
		return min;
	}
	
	private double calculate1(){
		Cordinate[] array = Arrays.copyOf(cordinates, N);
//		Arrays.sort(array);
		mysort(array);
//		print(array,0,N);
		Cordinate[] sortArray = Arrays.copyOf(array, 2*N);
		for(int i=N,j=0;j<N;i++,j++){
			sortArray[i]=array[j];
		}
		double rlt = tsp(sortArray, 0 , N-1, N);
//		print(sortArray, 0, N );
		return rlt;
	}
	
	private double tsp(Cordinate[] array, int low, int high, int offset){
		int newoffset = (offset+N)%(2*N);
		if(low==high){
			// Base case
			array[newoffset+low] = array[offset+low];
			return 0;
		}
		int n = high - low;
		int mid = low+n/2;
		int current_low = offset+low, current_high = offset+high;
		int current_mid = offset+ mid;
		double len_of_left_cycle = tsp(array, low, mid, newoffset);
		double len_of_right_cycle = tsp(array, mid+1, high, newoffset);
		//Merge two sub-cycle.
		double min_distance = Double.MAX_VALUE;
		double len_of_two_cycle = len_of_left_cycle+len_of_right_cycle; 
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
				double new_len = len_of_two_cycle+(delta_distance1<delta_distance2?delta_distance1:delta_distance2);
				if(new_len<min_distance){
					min_distance = new_len;
					int k = newoffset+low;
					for(int m=current_low;m<=i;k++,m++){
						array[k]=array[m];
					}
					if(delta_distance1<delta_distance2){
						for(int m = j; m>current_mid; m--,k++){
							array[k] = array[m];							
						}
						for(int m=current_high;m>j;m--,k++){
							array[k] = array[m];
						}
					}else{
						for(int m = j+1; m<=current_high; m++,k++){
							array[k] = array[m];							
						}
						for(int m=current_mid+1;m<=j;m++,k++){
							array[k] = array[m];
						}
					}
					for(int m=i+1;m<=current_mid;k++,m++){
						array[k]=array[m];
					}
				}
			}
		}
		return min_distance;
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
		
		@Override
		public int compareTo(Cordinate o) {
			Random rand = new Random();
			int randInt = rand.nextInt(3);
			return randInt-1;
//			if(radius<o.radius)
//				return -1;
//			else if(radius > o.radius)
//			else if(x>o.x)
//			return 0;
		}
		
		public String toString(){
			return String.valueOf(id);
		}
	}
	
	private void print(Object[] array, int from, int to){
		StringBuilder str = new StringBuilder();
		for(int i=from;i<to;i++){
			str.append(array[i]+",");			
		}
		System.out.println(str.substring(0, str.length()-1));
	}
	
	private void mysort(Object[] array){
		Random rand = new Random();
		int cnt = rand.nextInt(N);
		for(int i =0;i<cnt;i++){
		int s = rand.nextInt(N);
		int t = rand.nextInt(N);
		Object temp = array[t];
		array[t] = array[s];
		array[s] = temp;
		}
	}
	
}
