package performance;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PerfTest {
	
	private final int N = 1000000000;//1000M
	
	long start_time = 0;
	long end_time = 0;

	@Test
	public void testCompareForObject() {
		System.out.print("I am testCompareForObject: ");
		Integer a = 1;
		Integer b = 2;
		for(int i=0;i<N;i++){
			a.compareTo(b);
		}
	}

	@Test
	public void testSwapForBasicType() {
		System.out.print("I am testSwapForBasicType: ");
		int a = 1;
		int b = 2;
		int temp = 0;
		for(int i=0;i<N;i++){
			temp = a;
			a = b;
			b = temp;
		}
	}
	
	@Test
	public void testSwapForObject() {
		System.out.print("I am testSwapForObject: ");
		Integer a = 1;
		Integer b = 2;
		Integer temp = 0;
		for(int i=0;i<N;i++){
			temp = a;
			a = b;
			b = temp;
		}
	}
	
	@Test
	public void testCompareForBasicType() {
		System.out.print("I am testCompareForBasicType: ");
		int a = 1;
		int b = 2;
		for(int i=0;i<N;i++){
			if(a>=b) ;
		}
	}

	@Before
	public void before() {
		start_time = System.currentTimeMillis();
	}
	
	@After
	public void after(){
		end_time = System.currentTimeMillis();
		long cost_time = end_time - start_time;
		System.out.println("The test cost "
				+ cost_time + " millionseconds.");
	}
}
