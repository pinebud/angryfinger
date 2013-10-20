package concurrency;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class WaitingRoom {
	
	private static WaitingRoom instance = null;
	
	private PriorityQueue<Visitor> queue = new PriorityQueue<Visitor>(19, new VisitorComparator());
	
	private WaitingRoomCondition roomCondition =  WaitingRoomCondition.SantaCanSleep;
	
	private WaitingRoom(){
		
	}
	
	public static WaitingRoom getInstance(){
		if(instance == null) 
			instance = new WaitingRoom();
		return instance;
	}
	
	public synchronized void enter(Visitor visitor){
		queue.add(visitor);
		visitor.hi();
	}
	
	public void leave(Visitor visitor){
		visitor.bye();
	}
	
	public synchronized void checkRoomCondition(){
		Iterator<Visitor> it = queue.iterator();
		int numOfDwarf = 0;
		while(it.hasNext()){
			Visitor visitor = it.next();
			if(visitor instanceof ReinDeer){
				roomCondition = WaitingRoomCondition.hasReinDeer;
				Santa.getInstance().wakeup();
				return;
			}
			else if(visitor instanceof Dwarf){
				numOfDwarf++ ;
			}
		}
		if(numOfDwarf>2){
			roomCondition = WaitingRoomCondition.TripleDwarfReady;
			Santa.getInstance().wakeup();
			return;
		}
		roomCondition = WaitingRoomCondition.SantaCanSleep;
	}
	
	public synchronized Visitor[] getVisitors(){
		Visitor[] visitors = null;
		switch(roomCondition){
		case hasReinDeer:		
			visitors = new Visitor[1];
			visitors[0] = queue.poll(); 
			visitors[0].setStatus(Status.meetwithSanta);
			System.out
					.println("Hi, Santa, Rein Deer need to see you");
			break;
		case TripleDwarfReady:
			visitors = new Visitor[3];
			for(Visitor v: visitors){
				v = queue.poll(); 
				v.setStatus(Status.meetwithSanta);
			}
			System.out
					.println("Hi, Santa, 3 dwarf need to see you.");
			break;
		default:
			System.out
					.println("...");
		}
		
		return visitors;
	}

	class VisitorComparator implements Comparator<Visitor>{

		@Override
		public int compare(Visitor o1, Visitor o2) {
			boolean isO1ReinDeer = o1 instanceof ReinDeer;
			boolean isO2ReinDeer = o2 instanceof ReinDeer;
			if(isO1ReinDeer&&!isO2ReinDeer){
				return -1;
			}
			else if(!isO1ReinDeer&&isO2ReinDeer){
				return 1;
			}
			return 0;
		}
		
	}
}
