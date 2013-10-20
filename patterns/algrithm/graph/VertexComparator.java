package graph;

import java.util.Comparator;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class VertexComparator implements Comparator<Comparable> {

	@Override
	public int compare(Comparable o1, Comparable o2) {
		return o1.compareTo(o2);
	}

}
