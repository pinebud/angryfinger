package datastructure.apps;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianMaintanence {
	
	private PriorityQueue<Integer> bigSetHeap = null;

	private PriorityQueue<Integer> smallSetHeap = null;
	
	private Integer[] inputArray = null;
	
	public MedianMaintanence(Integer[] inputArray){	
		this.inputArray = inputArray;
		int heapSize = (inputArray.length+1)/2; 
		bigSetHeap = new PriorityQueue<Integer>(heapSize);
		smallSetHeap = new PriorityQueue<Integer>(heapSize, new ReversedHeapComparator());
	}
	
	public int func(){
		int n = inputArray.length;
		int sum = 0;
		for(int i=0;i<n;i++){
			Integer e = inputArray[i];
			
		}
		return sum%10000;
	}
	
	@SuppressWarnings("rawtypes")
	class ReversedHeapComparator implements Comparator<Comparable>{

		@SuppressWarnings("unchecked")
		@Override
		public int compare(Comparable o1, Comparable o2) {
			return o2.compareTo(o1);
		}

		
	}
}
