package r25;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class R25 {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String>[] stringlist = new List[1];
		List<Integer> intList = Arrays.asList(42);
		Object[] objects = stringlist;
		objects[0] = intList;
		String s = stringlist[0].get(0);
		System.out.println(s);
		
		
		List<String> list = new ArrayList<String>(1);
//		List<Object> oList = list; //Has compile error
		
		String[] strArray = new String[1];
		Object[] objArray = strArray; // Just have warnning, the array is covariant
		
		List<List<String>> listList = new ArrayList<List<String>>(2);
		List<String> ll = new LinkedList<String>();
		List<String> al = new ArrayList<String>();
		listList.add(ll);
		listList.add(al);
		
		List<List<List<List<List<List<List<String>>>>>>> lllllll = new ArrayList(1);
	}

}
