package ThreadPool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ProcessorOne implements Runnable{
	private CountDownLatch latch;
	
	public ProcessorOne(CountDownLatch latch) {
		this.latch = latch;
	}
	@Override
	public void run() {
		try {
			System.out.println("Started ...");
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Completed ...");
		latch.countDown();
	}
}

public class App1 {

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(3);
		ExecutorService executor = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 3; i++) {
			executor.submit(new ProcessorOne(latch));
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done");

	}

}
