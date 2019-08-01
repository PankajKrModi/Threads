package ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable{

	private int id;
	public Processor(int id) {
		this.id = id;
	}
	@Override
	public void run() {
		System.out.println("Starting ... "+id);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Completed ... "+id);
	}
	
}
public class App {

	public static void main(String args[]) {
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		for (int i = 0; i < 5; i++) {
			executor.execute(new Processor(i+1));
		}
		executor.shutdown();
		try {
			executor.awaitTermination(5, TimeUnit.MICROSECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("All tasks completed");
	}
}
