package grammar;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ReferenceTest {

	@Test
	public void test(){
		Set<Myclass> set = new HashSet<Myclass>();
		Myclass mclass = new Myclass("1");
		set.add(mclass);
		mclass = new Myclass("2");
		set.add(mclass);
		mclass = new Myclass("3");
		set.add(mclass);
		for(Myclass m: set){
			System.out.println(m.getId());
		}
	}

	class Myclass{
		String id = "";
		public Myclass(String id){
			this.id = id;
		}
		public String getId(){
			return id;
		}
	}
}
