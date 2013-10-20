package factory;

import sort.*;
import sort.impl.BubbleSort;
import sort.impl.MergeSort;
import sort.impl.NullSort;
import sort.impl.QuickSort;

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
