package search;

public class BinarySearch {
	public static int find(int[] inputArray, int a){
		int low = 0, high = inputArray.length;
		int curIndex = 0;
		while(low<high){
			curIndex = low+((high - low)>>>1);
			int curElement = inputArray[curIndex];
			if(a==curElement){
				return curIndex;
			}else if(a<curElement){
				high>>>=1;
			}else{
				low = curIndex+1;
			}
		}
		return -1;
	}
}
