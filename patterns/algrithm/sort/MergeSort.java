package sort;


public class MergeSort implements ISort{

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object[] sort(Object[] rawArray, SortOrder order) {
		int n = rawArray.length;
		if(rawArray.length==1)
			return rawArray;
		
		Object[] sortedList = new Object[n];//new ArrayList<T>();		
		int halfOfN = n / 2;
		Object[] firstHalfSortedArray = sort(subArray(rawArray, 0,halfOfN), order);
		Object[] secondHalfSortedArray = sort(subArray(rawArray, halfOfN,n), order);
		int i=0,j=0;
		for(int k=0;k<n;k++){
			
			if(i==halfOfN){
				for(int l=k;l<n;l++,j++){
					sortedList[l]=secondHalfSortedArray[j];
				}
				break;
			}
			if(j==n-halfOfN){
				for(int l=k;l<n;l++,i++){
					sortedList[l]=firstHalfSortedArray[i];
				}
				break;
			}
			
			Comparable a = (Comparable) firstHalfSortedArray[i];
			Comparable b = (Comparable) secondHalfSortedArray[j];
			if(SortOrder.asc==order){
				if(a.compareTo(b)>0){
					sortedList[k]=b;	
					j++;
				}else{
					sortedList[k]=a;
					i++;
				}
			}else{
				if(a.compareTo(b)<0){
					sortedList[k]=b;	
					j++;
				}else{
					sortedList[k]=a;
					i++;
				}
			}
			
		}
		
		return sortedList;
	}
	
	/**
	 * 
	 * @param rawArray
	 * @param start minValue = 0
	 * @param end maxValue = rawArray.length
	 * @return
	 */
	private Object[] subArray(Object[] rawArray, int start, int end){
		if(start==end){
			return new Object[0];
		}
		Object[] newArray = new Object[end-start];
		for(int i=start, k=0;i<end;i++,k++){
			newArray[k]=rawArray[i];
		}
		return newArray;
	}

	
}
