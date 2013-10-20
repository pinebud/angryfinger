package concurrency;

public class Myhand {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for(int i=0;i<5;i++){
			new Thread(new Dwarf("Dwarf"+i)).start();			
		}
		for(int i=0;i<2;i++){
			new Thread(new ReinDeer("ReinDeer"+i)).start();
		}
		new Thread(Santa.getInstance()).start();

	}

}
