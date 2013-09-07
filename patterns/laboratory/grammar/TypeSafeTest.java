package grammar;

import junit.framework.Assert;

import org.junit.Test;

public class TypeSafeTest {

	@Test
	public void testIntToFloat(){
		int a = 6, b = 5;
		float rlt1=a/b;
		float c = a;
		float d = b;
		float rlt2 = c/d;
		Assert.assertTrue(1.0==rlt1);
		if(rlt2==1.2)
			System.out.println("yes");
		else
			System.out.println("no");
//		Assert.assertTrue(1.2==rlt2); // can't work
		
//		float rlt3 = 1.3;// compile error
//		Assert.assertTrue(1.3==rlt3);
	}
	
	@Test
	public void testIntToDouble(){
		int a = 6, b = 5;
		double rlt1=a/b;
		double c = a;
		double d = b;
		double rlt2 = c/d;
		Assert.assertTrue(1.0==rlt1);
		if(rlt2==1.2)
			System.out.println("yes");
		else
			System.out.println("no");
		Assert.assertTrue(1.2==rlt2); // can't work
		
		double rlt3 = 1.3;
		Assert.assertTrue(1.3==rlt3);
	}
}
