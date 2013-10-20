package sort.impl;

import sort.ISort;
import sort.SortOrder;

public class NullSort implements ISort {

	@Override
	public Object[] sort(Object[] rawArray, SortOrder order) {	
		System.err.println("Hi, I'm NullSort. You may forget to add your sort to SortFactory");
		return null;
	}

}
