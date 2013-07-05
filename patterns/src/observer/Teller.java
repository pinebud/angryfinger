package observer;

import java.util.*;

import org.junit.Test;

public class Teller {
	
	private List<IListener> listeners = new ArrayList<IListener>();

	public Teller() {
		
    final int n = 1; //[Learning Point] this field has to be final, otherwise the listener could access it.
	
	listeners.add(new IListener(){

			@Override
			public void gotIt(String message) {
				System.out.println("I'm Mike, and I hear "+n+" word: "+ message);		// Here the inline method access host class's non-private field.	
			}
			
		});
		
	listeners.add(new IListener(){
		
		@Override
		public void gotIt(String message) {
			System.out.println("I'm Jack, Parden?");		// Here the inline method access host class's non-private field.	
		}
		
	});
	
	}
	
		
	public void shout(){
		for(IListener listener: listeners){
			listener.gotIt("finger");
		}
	}
	
	@Test
	public void testSound(){
		Teller teller = new Teller();
		teller.shout();
	}

}
