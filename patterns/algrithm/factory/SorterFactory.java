package factory;

import sort.BubbleSort;
import sort.ISort;
import sort.MergeSort;
import sort.SortType;

public class SorterFactory {
	
	public static ISort getSorter(SortType type){
		switch(type){
		case bubble: return new BubbleSort();
		default: return new MergeSort();
		}
	}
}
