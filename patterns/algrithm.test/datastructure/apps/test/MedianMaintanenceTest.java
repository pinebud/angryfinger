package datastructure.apps.test;

import junit.framework.Assert;

import org.junit.Test;

import datastructure.apps.MedianMaintanence;

public class MedianMaintanenceTest {

	@Test
	public void test() {
		MedianMaintanence instance = new MedianMaintanence(new Integer[]{1,7,2,8,5});
		int rlt = instance.func(); //1+1+2+2+5=11
		Assert.assertEquals(11, rlt);
	}

}
