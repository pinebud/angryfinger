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
		if(heapSize>0){
			bigSetHeap = new PriorityQueue<Integer>(heapSize);
			smallSetHeap = new PriorityQueue<Integer>(heapSize, new ReversedHeapComparator());
		}else{

			bigSetHeap = new PriorityQueue<Integer>();
			smallSetHeap = new PriorityQueue<Integer>(10, new ReversedHeapComparator());
		}
	}

	public int func(){
		int n = inputArray.length;
		if(n<1)
			return -1;
		int sum = inputArray[0];
		smallSetHeap.add(inputArray[0]);
		for(int i=1;i<n;i++){
			Integer e = inputArray[i];
			int smallSetSize = smallSetHeap.size();
			int bigSetSize = bigSetHeap.size();
			int sizeInterval = bigSetSize-smallSetSize;
			Integer maxInSmallSet = smallSetHeap.peek();
			
			if(e.compareTo(maxInSmallSet)<0){
				smallSetHeap.add(e);
			}else{
				bigSetHeap.add(e);
			}
			
			sizeInterval = bigSetHeap.size() - smallSetHeap.size();
			
			if(sizeInterval>0){
				smallSetHeap.add(bigSetHeap.poll());
			}else if(sizeInterval<-1){
				bigSetHeap.add(smallSetHeap.poll());
			}
			
			int median = smallSetHeap.peek();
			sum+=median;
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
