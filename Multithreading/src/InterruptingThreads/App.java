package InterruptingThreads;

import java.util.Random;

public class App {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Starting...");
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Random random = new Random();
				for(int i=0;i<1E7;i++) {
					//If we dontcheckfor thread interruption it will continue running, becoz 
					//only bool flag is set if it is interrupted 
//					if(Thread.currentThread().isInterrupted()) {
//						System.out.println(Thread.currentThread().getName()+" is interrupted");
//						break;
//					}
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						System.out.println("Interrupted!");
						break;//on interruption exits the thread
					}
					Math.sin(random.nextDouble());
				}
			}
		});
		Long start = System.currentTimeMillis();
		t1.start();
		
		Thread.sleep(500);
		
		t1.interrupt();
		
		t1.join();
		
		Long end = System.currentTimeMillis();
		System.out.println("Duration of run: "+(end-start)+" millisec");
		System.out.println("Finished.");
	}

}
