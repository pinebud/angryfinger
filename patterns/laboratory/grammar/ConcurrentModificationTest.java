package grammar;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ConcurrentModificationTest {
	
	@Test
	public void testObjectReference(){
		List<TestObject> list1 = new ArrayList<TestObject>();
		List<TestObject> list2 = new ArrayList<TestObject>();
		TestObject to = new TestObject("NJ");
		list1.add(to);
		list2.add(to);
		System.out.println(list1.get(0).toString());
		System.out.println(list2.get(0).toString());
	}
	
	@Test
	public void testConcurrentModification(){
		List<TestObject> list1 = new ArrayList<TestObject>();
		List<TestObject> list2 = new ArrayList<TestObject>();
		TestObject to = new TestObject("NJ");
		list1.add(to);
		to = new TestObject("KK");
		list1.add(to);
		for(TestObject o: list1){
			list2.add(o);
		}
		System.out.println(list1.get(0).toString());
		System.out.println(list2.get(0).toString());
		System.out.println(list1.get(1).toString());
		System.out.println(list2.get(1).toString());
	}
	
	class TestObject{
		String me;
		public TestObject(String name){
			me = name;
		}
		
		public String toString(){
			return me;
		}
	}

}
