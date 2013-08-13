package sort.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import sort.ISort;
import sort.SortOrder;

/**
 * The pivot will be swapped to the very left of the input array.
 * @author jingning
 *
 */
public class QuickSort implements ISort {	

	enum PivotSelectionStrategy{
		FirstElement, FinalElement, MedianElement
	}
	
	private long numOfComparison = 0;
	
	public long getNumOfComparison(){
		return numOfComparison;
	}
	
	@Override
	public Object[] sort(Object[] rawArray, SortOrder order) {
		int n = rawArray.length;
		
		numOfComparison=0;
		Object[] inputArray1 = Arrays.copyOf(rawArray, n);
		quicksort(inputArray1, 0, n, order, PivotSelectionStrategy.FirstElement);
		System.out.println(numOfComparison);
		
		numOfComparison=0;
		Object[] inputArray2 = Arrays.copyOf(rawArray, n);
		quicksort(inputArray2, 0, n, order, PivotSelectionStrategy.FinalElement);
		System.out.println(numOfComparison);
		
		numOfComparison=0;
		Object[] inputArray3 = Arrays.copyOf(rawArray, n);
		quicksort(inputArray3, 0, n, order, PivotSelectionStrategy.MedianElement);
		System.out.println(numOfComparison);
		
		return rawArray;
	}
	
	/**
	 * The pivot is always selected with the 1st element of the input array
	 * @param rawArray : the to be sorted input array
	 * @param offset : offset of the subroutine array with the input array
	 * @param n : the size of the array in subroutine
	 * @param order: The value would be SortOrder.asc or SortOrder.desc.
	 * @param numOfComparations: used to compute the number of comparisons happened during the sort.
	 * @return the number of comparisons in subroutine.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void quicksort(Object[] rawArray, int offset, int n, SortOrder order, PivotSelectionStrategy strategy){
		if(n<=1){
			return;
		}
		numOfComparison+=n-1;
		
		int positionOfPivot = choosePivot(rawArray, offset,  n, strategy);
		Object pivot = rawArray[positionOfPivot];	
		swap(rawArray,offset, positionOfPivot);
		
		int i=offset+1, j=offset+1;		
		for(int k = 1;k<n;k++){
			Comparable a = (Comparable) rawArray[j++];		
			if(order==SortOrder.asc){
				if(a.compareTo(pivot)<0){
					swap(rawArray, i, j-1);
					i++;
				}
			}else{
				if(a.compareTo(pivot)>0){
					//To do: to be filled
					swap(rawArray, i, j-1);
					i++;
				}
			}
		}
		swap(rawArray,offset,i-1);
		quicksort(rawArray, offset,i-offset-1, order, strategy);
		quicksort(rawArray, i,n+offset-i, order,strategy);		
	}
	
	private void swap(Object[] array, int i, int j){
		Object temp = array[i];
		array[i] = array[j];
		array[j]=temp;
	}
	
	
	private int choosePivot(Object[] rawArray, int offset, int n,  PivotSelectionStrategy strategy){
		switch(strategy){
		case FirstElement: 
			return offset;
		case FinalElement:
			return offset+n-1;
		default:
			return chooseObjectWithMedianValue(rawArray, offset, n);
		}
	}
	
	/**
	 * This Method is the used to find the median value of the first, final and middle element of the array in subroutine.
	 * E.g., if the {first, middle, final} is {1,8,4}, the 4 is the median value, so the pivot will be 4. 
	 * @param rawArray : the to be sorted input array
	 * @param offset : offset of the subroutine array with the input array
	 * @param n : the size of the array in subroutine
	 * @return the index of the chosen pivot in input array
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private int chooseObjectWithMedianValue(Object[] rawArray, int offset, int n){

		/*
		 *  At most, the comparison may happened 3 times: a compare b, c compare a, c compare b;
		 *  At least the comparison may happened 2 times: a compare b, c compare a or c compare b;
		 * 
		 */
		numOfComparison+=3; 
		
		Comparable a = (Comparable) rawArray[offset];		//first
		Comparable b = (Comparable) rawArray[offset+(n-1)/2];//middle
		Comparable c = (Comparable) rawArray[offset+n-1];   //final	
		
		Comparable pivot = a;
		
		List<Comparable> list  = new ArrayList<Comparable>(3);
		if(a.compareTo(b)<=0){
			list.add(a);
			list.add(b);
		}
		else{
			list.add(b);
			list.add(a);
		}
		
		if(list.get(0).compareTo(c)>=0){ 
			pivot = list.get(0);
//			numOfComparison--; // the next else if will not be reachable, so the comparison times is 3-1 =2 .
		}	
		else if(list.get(1).compareTo(c)<=0){
			pivot = list.get(1);
		}else
			pivot = c;
		
		if(pivot == a)
			return offset;
		else if(pivot == b){
			return offset+(n-1)/2;
		}else{
			return offset+n-1;
		}
						
	}
	
	@Test public void test(){
		Assert.assertEquals(1, chooseObjectWithMedianValue(new Integer[]{1,5,6}, 0 , 3));
		
		Assert.assertEquals(2, chooseObjectWithMedianValue(new Integer[]{1,6,5}, 0 , 3));
		
		Assert.assertEquals(0, chooseObjectWithMedianValue(new Integer[]{5,1,6}, 0 , 3));
		
		Assert.assertEquals(0, chooseObjectWithMedianValue(new Integer[]{5,6,1}, 0 , 3));
		
		Assert.assertEquals(2, chooseObjectWithMedianValue(new Integer[]{6,1,5}, 0 , 3));
		
		Assert.assertEquals(1, chooseObjectWithMedianValue(new Integer[]{6,5,1}, 0 , 3));
	}
};
