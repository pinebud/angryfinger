package factory;

import sort.*;

public class SorterFactory {
	
	public static ISort getSorter(SortType type){
		switch(type){
		case bubble: return new BubbleSort();
		case merge: return new MergeSort();
		case quicksort: return new QuickSort();
		default: return new NullSort();
		}
	}
}
