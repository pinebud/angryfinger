package greedy;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import greedy.apps.WiseJobScheduler;

import org.junit.Assert;
import org.junit.Test;

import others.Constants;
import util.FileUtility;

public class WiseJobSchedulerTest {
	private static final File inputFile = new File(Constants.RESOURCE_FOLDER+"/greedy/jobs.txt");

	@Test
	public void testSmoke() {
		List<String> weight_length_list = new ArrayList<String>();
		weight_length_list.add("5");
		weight_length_list.add("3 1");//id=1
		weight_length_list.add("2 2");//id=2
		weight_length_list.add("1 3");//id=3
		weight_length_list.add("1 4");//id=4
		weight_length_list.add("5 3");//id=5
		WiseJobScheduler wjs = new WiseJobScheduler(weight_length_list);
		long a = wjs.scheduleJobsWithDifferFactor();
		Assert.assertArrayEquals(new String[]{"5","1","2","3","4"}, wjs.getScheduledJobsInId());
		Assert.assertEquals(61, a); //5*3+3*4+2*6+1*9+1*13=61
		a = wjs.scheduleJobsWithRateFactor();
		Assert.assertArrayEquals(new String[]{"1","5","2","3","4"}, wjs.getScheduledJobsInId());
		Assert.assertEquals(57, a);//3*1+5*4+2*6+1*9+1*13=57
	}
	
	@Test
	public void quiz(){
		List<String> weight_length_list = FileUtility.readFileByLineAsStringList(inputFile);
		WiseJobScheduler wjs = new WiseJobScheduler(weight_length_list);
		long a = wjs.scheduleJobsWithDifferFactor();
		System.out.println("Q1: "+a);
		a = wjs.scheduleJobsWithRateFactor();
		System.out.println("Q2: "+a);
	}
	
	   @Test
       public void testOdd() {
            
      }
      
       @Test
       public void testEven() {
             
      }
      
       @Test
       public void testSingleElement() {
      }
      
       @Test
       public void testDup(){
      }
      
       @Test
       public void testEmptyInputArray(){
             
      }
      
       @Test
       public void testForMinAndMaxInteger(){
             
      }

       @Test
       public void testRandomInput(){
             
      }
      
       @Test
       public void testLargeVolume(){
             
      }


}
