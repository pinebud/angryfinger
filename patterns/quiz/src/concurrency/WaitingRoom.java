package concurrency;

import java.util.LinkedList;
import java.util.Queue;

public class WaitingRoom {
	
	private static WaitingRoom instance = null;
	
	private Queue<Visitor> queue = new LinkedList<Visitor>();
	
	private WaitingRoomCondition condition = WaitingRoomCondition.SantaCanSleep;

	private WaitingRoom(){
		
	}
	
	public static WaitingRoom getInstance(){
		if(instance == null) 
			instance = new WaitingRoom();
		return instance;
	}
	
	public void enter(Visitor visitor){
		queue.add(visitor);
	}
	
	public void leave(Visitor visitor){
		queue.remove(visitor);
	}
	
	public WaitingRoomCondition conditionInWaitingRoom(){
		condition = WaitingRoomCondition.howIsGoing(queue);
		return condition;
	}

}
