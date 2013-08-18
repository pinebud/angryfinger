
package datastructure.apps;

import java.util.Hashtable;

public class Probe2Sum {
	
	public static int probe2Sum(Long[] inputArray, int lowbound, int upperbound){
		int numOfT =0;
		Hashtable<Long,Integer> table = new Hashtable<Long,Integer>();
		for(Long x:inputArray){
			if(table.get(x)==null)
				table.put(x, 0);
			else
				table.put(x, 1);
		}
		for(int t=lowbound;t<=upperbound;t++){
			for(long x: inputArray){
				long y = t-x;
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
