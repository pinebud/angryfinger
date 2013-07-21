package factory;

import java.util.*;

public class DataFactory {
	
	/**
	 * {4,5,6,8,3,7,2,1};
	 * @return
	 */
	public static List<Integer> generateUnsortedIntList(){
		List<Integer> list = new ArrayList<Integer>();
		list.add(4);list.add(5);list.add(6);list.add(8);
		list.add(3);list.add(7);list.add(2);list.add(1);		
		return list;
	}
}
