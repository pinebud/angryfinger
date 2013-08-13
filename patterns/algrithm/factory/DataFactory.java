package factory;

import java.io.File;
import java.util.*;

import util.FileUtility;

public class DataFactory {
	
	public static final File TEST_DATA_FILE = new File(System.getProperty("user.dir")+"/test.data");
	
	private static final int BATCH_SIZE = 100000;
	
	public static int[] generateRandomIntArray(){	
		int[] intArray = new int[BATCH_SIZE];
		Random rnd = new Random();
		for(int i=0;i<BATCH_SIZE;i++){
			intArray[i]=rnd.nextInt(BATCH_SIZE);
		}
		return intArray;
	}
	
	public static Integer[] generateReversedSortedIntArray(){
		Integer[] intArray = new Integer[BATCH_SIZE];
		for(int i=BATCH_SIZE-1;i>=0;i--){
			intArray[i]=i;
		}
		return intArray;
	}
	
	public static String serialize(int[] intArray){
		StringBuilder sbuilder = new StringBuilder();
		int n = intArray.length;
		for(int i=0;i<n;i++){
			sbuilder.append(intArray[i]+",");
		}
		return sbuilder.substring(0, sbuilder.length()-1);
	}
	
	/**
	 * Normally the size of strArray should be equal to BATCH_SIZE
	 * @param intStringArray
	 * @return
	 * @throws InvalidParsedArraySize 
	 */
	public static int[] unserialize(String intStringArray) throws InvalidParsedArraySize{
		String[] strArray = intStringArray.split(",");
		int n = strArray.length;
		if(n!=BATCH_SIZE)
			throw new InvalidParsedArraySize();
		int[] intArray = new int[n];
		for(int i=0;i<n;i++){
			intArray[i]= Integer.valueOf(strArray[i]);
		}
		return intArray;
	}
	
	public static void main(String[] args){
		FileUtility.appendLine(TEST_DATA_FILE, serialize(generateRandomIntArray()));
	}
	
	
	
}
