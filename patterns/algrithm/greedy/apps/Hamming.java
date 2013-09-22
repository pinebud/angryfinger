package greedy.apps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hamming {

	private Map<Integer, Integer> map = new HashMap<Integer, Integer>();

	private Integer[] keyArray = null;

	private int[] maskArray = null;

	private int numOfBits = 0;

	public Hamming(List<String> rawList) {
		String[] pair = rawList.get(0).split(" ");
		numOfBits = Integer.valueOf(pair[1]);

		initBitMaskArray();

		int n = Integer.valueOf(pair[0]);
		keyArray = new Integer[n];
		for (int i = 0; i < n; i++) {
			String bitString = rawList.get(i + 1).replace(" ", "").trim();
			Integer key = Integer.valueOf(bitString, 2);
			Integer value = map.get(key);
			if (value == null) {// Remove the duplicated hamming code
				map.put(key, 0);
				keyArray[i] = key;
			}
		}
	}

	public int calculate() {
		int seq = 1;
		int n = keyArray.length;
		for (int i = 0; i < n; i++) {
			Integer key = keyArray[i];
			if(key==null)
				continue;
//			Integer value = map.get(key);
			int groupid = func(key, seq);
			map.put(key, groupid);
			if (groupid == seq)
				seq++;
		}
		return seq-1;
	}

	private int func(Integer number, int seq) {
		int groupid = seq;
		int n = maskArray.length;
		int[] tempArray = new int[n];
		for (int i = 0; i < n; i++) {
			int mask = maskArray[i];
			int a = number & mask;
			Integer targetInt = (number & (~a)) | (a ^ mask);
			Integer value = map.get(targetInt);
			if (value != null) {
				tempArray[i] = targetInt;
				if (value!=0&&value < groupid) {
					groupid = value;
				}
			} else {
				tempArray[i] = -1;
			}
		}
		for (int i = 0; i < n; i++) {
			int temp = tempArray[i];
			if (temp != -1) {
				map.put(temp, groupid);
			}
		}
		return groupid;
	}

	private void initBitMaskArray() {
		int n = numOfBits + numOfBits * (numOfBits - 1);
		maskArray = new int[n];
		maskArray[0] = 1;
		for (int i = 1; i < numOfBits; i++) {
			maskArray[i] = maskArray[i - 1] << 1;
		}
		int k = numOfBits;
		for (int i = 0; i < numOfBits; i++) {
			for (int j = 0; j < numOfBits; j++) {
				if (i != j) {
					maskArray[k++] = maskArray[i] | maskArray[j];
				}
			}
		}
	}

}
