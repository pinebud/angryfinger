package concurrency;

public class Santa implements Runnable{

	@Override
	public void run() {
		WaitingRoomCondition cond = WaitingRoom.getInstance().conditionInWaitingRoom();
		switch(cond){
		case hasReinDeer: System.out.println("Rein Deer, let's go to deliver presents");
		case TripleDwarfReady: System.out.println("Hi, little men, let's see how to make fun toys.");
		default:
			System.out.println("OK, done, I'm going to sleep, call me later.");
		}
		
	}

}
