package datastructure;

import java.util.Collection;
import java.util.Comparator;

public interface IUnion<T> {
	
	public void union(IUnion<T> unit);
	
	public boolean has(T t, Comparator<T> comparator);
	
	public Collection<T> getElements();
}
