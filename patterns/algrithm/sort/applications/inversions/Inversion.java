package sort.applications.inversions;

public class Inversion {
	public static long findInversions(Object[] rawArray) {
		return mergeSort(rawArray, rawArray.clone(), 0, rawArray.length, 0, 0);
	}

	public static long mergeSort(Object[] src, Object[] dest, int low, int high,
			int off, long numOfInversions) {
		int length = high - low;
		if(length<=1)
			return numOfInversions;

		// Recursively sort halves of dest into src
		int destLow = low;
		int destHigh = high;
		low += off;
		high += off;
		int mid = (low + high) >>> 1;
		numOfInversions = mergeSort(dest, src, low, mid, -off, numOfInversions);
		numOfInversions = mergeSort(dest, src, mid, high, -off, numOfInversions);

		// If list is already sorted, just copy from src to dest. This is an
		// optimization that results in faster sorts for nearly ordered lists.
		if (((Comparable) src[mid - 1]).compareTo(src[mid]) <= 0) {
			System.arraycopy(src, low, dest, destLow, length);
			return numOfInversions;
		}

		// Merge sorted halves (now in src) into dest
		for (int i = destLow, p = low, q = mid; i < destHigh; i++) {
			if (q >= high || p < mid
					&& ((Comparable) src[p]).compareTo(src[q]) <= 0)
				dest[i] = src[p++];
			else{
				dest[i] = src[q++];
				numOfInversions+=mid-p;
			}
		}

		return numOfInversions;
	}

	/**
	 * 
	 * @param rawArray
	 * @param start
	 *            minValue = 0
	 * @param end
	 *            maxValue = rawArray.length
	 * @return
	 */
	private static Object[] subArray(Object[] rawArray, int start, int end) {
		if (start == end) {
			return new Object[0];
		}
		Object[] newArray = new Object[end - start];
		for (int i = start, k = 0; i < end; i++, k++) {
			newArray[k] = rawArray[i];
		}
		return newArray;
	}

}
