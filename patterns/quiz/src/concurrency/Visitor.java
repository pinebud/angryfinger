package concurrency;

public abstract class Visitor implements Runnable {
	protected Status status = Status.hangingout;

	protected long pendingtime = 10000;

	protected String name = "changeme";

	public Visitor(String name) {
		this.name = name;
		System.out.println("Hi, I'm " + name);
	}

	public void hi() {
		status = Status.waiting;
		System.out.println(name+" is back now");
	}

	public void bye() {
		status = Status.hangingout;
		System.out.println(name + "says bye");
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public String getName(){
		return name;
	}

	public void run() {
		while (true) {
			try {
				WaitingRoom.getInstance().enter(this);
				while (status != Status.meetingover) {
					Thread.sleep(1000);
				}
				WaitingRoom.getInstance().leave(this);
				Thread.sleep(pendingtime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
