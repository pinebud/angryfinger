package concurrency;

public class Santa implements Runnable {
	
	private static Santa instance = null;
	
	private boolean sleep = true;
	
	private Santa(){
		System.out.println("Hey, Santa is here! Z.Z.Z...");
	}
	
	public static Santa getInstance(){
		if(instance==null){
			instance = new Santa();
		}
		return instance;
	}
	
	public boolean isSleep(){
		return sleep;
	}
	
	public void wakeup(){
		sleep = false;
	}
	
	private void sleep(){
		sleep = true;
		System.out.println("Z.Z.Z...");
	}

	@Override
	public void run() {
		while (true) {
			try {				
				if(!sleep){
				Visitor[] visitors = WaitingRoom.getInstance().getVisitors();
				if (visitors.length == 1) {
					System.out
							.println("Rein Deer, let's go to deliver presents, it would take 5 seconds");
					Thread.sleep(5000);
					visitors[0].setStatus(Status.meetingover);
				} else if (visitors.length == 3) {
					for(Visitor visitor: visitors){
						System.out.println(visitor.getName()+", come in");
					}
					System.out.println("Now let me teach you how to make new toys, it would take 3 seconds");
					Thread.sleep(3000);
					for(Visitor visitor: visitors){
						System.out.println(visitor.getName()+", you can go back to make toys now");
						visitor.setStatus(Status.meetingover);
					}
				}
				sleep();
				}else{
					WaitingRoom.getInstance().checkRoomCondition();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
