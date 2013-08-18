package datastructure.apps;

import java.util.Hashtable;

public class Probe2Sum {
	
	public static int probe2Sum(Integer[] inputArray, int lowbound, int upperbound){
		int numOfT =0;
		Hashtable<Integer,Integer> table = new Hashtable<Integer,Integer>();
		for(Integer x:inputArray){
			if(table.get(x)==null)
				table.put(x, 0);
			else
				table.put(x, 1);
		}
		for(int t=lowbound;t<=upperbound;t++){
			for(int x: inputArray){
				int y = t-x;
				Integer value = table.get(y);
				if(value!=null){
					if(y!=x||value==1){//to solve the repetitions
					System.out.println(x+"+"+y+"="+t);
					numOfT++;
					break;
					}
				}
			}
		}
		return numOfT;
	}

}
