package concurrency;

public class ReinDeer extends Visitor implements Runnable {
	
	public ReinDeer(String name){
		super(name);
		pendingtime = 20000;
	}

}
