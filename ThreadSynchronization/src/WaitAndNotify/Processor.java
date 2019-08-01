package WaitAndNotify;

import java.util.Scanner;

public class Processor {

	public void produce() throws InterruptedException {
		synchronized (this) {
			System.out.println("Producer thread running...");
			wait();//hand over lock to other thread, should be called from synchronized block 
			System.out.println("Producer Thread Resumed...");
		}
	}
	public void consume() throws InterruptedException {
		Thread.sleep(1000);
		Scanner in = new Scanner(System.in);
		synchronized (this) {		
			
			System.out.println("Waiting for return key");
			in.nextLine();
			System.out.println("Return key pressed");
			notify();//does not handover lock until synchronized block completes ,notifies other thread,should be called from synchronized block
			Thread.sleep(5000);
		}
	}
}
