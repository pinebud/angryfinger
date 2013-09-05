package greedy.apps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class WiseJobScheduler {
	private Job[] jobList = null;
	
	public WiseJobScheduler(List<String> weight_length_list){
		int n = Integer.valueOf(weight_length_list.get(0));
		jobList = new Job[n];
		for(int i = 1;i<=n;i++){
			String[] pair = weight_length_list.get(i).split(" ");
			Job job = new Job(String.valueOf(i), Integer.valueOf(pair[0].trim()), Integer.valueOf(pair[1].trim()));
			jobList[i-1] = job;
		}
	}
	
	public long scheduleJobsWithDifferFactor(){
		Arrays.sort(jobList, new JobDifferComparator());
		return calculateWeightedCompletionSum();
	}
	
	public long scheduleJobsWithRateFactor(){
		Arrays.sort(jobList, new JobRateComparator());		
		return calculateWeightedCompletionSum();
	}
	
	private long calculateWeightedCompletionSum(){
		long sum = 0;
		long cTime = 0;
		for(Job job:jobList){
			cTime += job.getLength();
			sum+=job.getWeight()*cTime;
		}
		return sum;
	}
	
	public String[] getScheduledJobsInId(){
		int n = jobList.length;
		String[] jobIdArray = new String[n];
		for(int i =0;i<n;i++){
			jobIdArray[i] = jobList[i].getId();
		}
		return jobIdArray;
	}
	
	class Job{
		private String id;
		private int weight = 0, length =0;
		public Job(String id, int weight, int length){
			this.id = id;
			this.weight = weight;
			this.length = length;
		}
		
		public String getId(){
			return id;
		}
		
		public int getWeight(){
			return weight;
		}
		public int getLength(){
			return length;
		}
		
	}
	
	class JobRateComparator implements Comparator<Job>{

		@Override
		public int compare(Job o1, Job o2) {
			float rate1 = o1.getWeight()/o1.getLength();
			float rate2 = o2.getWeight()/o2.getLength();
			if(rate1<rate2)
				return 1;
			else if(rate1>rate2)
				return -1;
			return 0;
		}
		
	}
	
	class JobDifferComparator implements Comparator<Job>{

		@Override
		public int compare(Job o1, Job o2) {
			int differ1 = o1.getWeight() - o1.getLength();
			int differ2 = o2.getWeight() - o2.getLength();
			if(differ1<differ2)
				return 1;
			else if(differ1>differ2)
				return -1;
			else{
				if(o1.getWeight()<o2.getWeight())
					return 1;
				else if(o1.getWeight()>o2.getWeight())
					return -1;
			}
			return 0;
		}
		
	}

}
