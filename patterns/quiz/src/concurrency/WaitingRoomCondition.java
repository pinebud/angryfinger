package concurrency;

import java.util.Iterator;
import java.util.Queue;

public enum WaitingRoomCondition {
	hasReinDeer, TripleDwarfReady, SantaCanSleep;
	public static WaitingRoomCondition howIsGoing(Queue<Visitor> queue){
		Iterator<Visitor> it = queue.iterator();
		int numOfDwarf = 0;
		while(it.hasNext()){
			Visitor visitor = it.next();
			if(visitor instanceof ReinDeer){
				return hasReinDeer;
			}
			else if(visitor instanceof Dwarf){
				numOfDwarf++ ;
			}
		}
		if(numOfDwarf>2){
			return TripleDwarfReady;
		}
		return SantaCanSleep;
	}
}
