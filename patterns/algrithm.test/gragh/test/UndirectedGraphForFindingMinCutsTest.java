package gragh.test;

import graph.mincuts.UndirectedGraphForFindingMinCuts;

import java.io.File;
import java.util.Arrays;

import org.junit.Test;

import others.Constants;
import util.FileUtility;

public class UndirectedGraphForFindingMinCutsTest {
	
	private static final File quizInputFile = new File(Constants.RESOURCE_FOLDER+"/graph/mincuts/kargerMinCut.txt");
	private static final File simpleInputFile = new File(Constants.RESOURCE_FOLDER+"/graph/mincuts/simple.txt");
	
	@Test
	public void quiz() {
		int numberOfIteration = 100;
		int[] rlts = new int[numberOfIteration];
		for(int i=0;i<numberOfIteration;i++){
			UndirectedGraphForFindingMinCuts instance = new UndirectedGraphForFindingMinCuts(FileUtility.readFileByLineAsStringList(quizInputFile));
			rlts[i]=instance.findMinCuts();
			System.out.println(rlts[i]);
		}
		Arrays.sort(rlts);
		System.out.println("Min="+rlts[0]);
	}
	
	@Test
	public void testSimpleCase(){
		for(int i=0;i<10;i++){
			UndirectedGraphForFindingMinCuts instance = new UndirectedGraphForFindingMinCuts(FileUtility.readFileByLineAsStringList(simpleInputFile));
//			Assert.assertEquals(1, instance.findMinCuts());
			System.out.println(instance.findMinCuts());
		}
	}

}
