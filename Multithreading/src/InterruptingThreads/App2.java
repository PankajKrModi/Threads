package InterruptingThreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App2 {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newSingleThreadExecutor(); //Executor is async framework
		executor.submit(()->{
			printNo();
			printNo();
		});
		Thread.sleep(3000);
		executor.shutdownNow();//interrupts running thread, set its status to true, prevents waiting thread to run
		executor.awaitTermination(3000, TimeUnit.MILLISECONDS);//waits for 3 sec to stop all running threads
	}

	private static void printNo() {
		for (int i = 0; i < 9; i++) {
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
				Thread.currentThread().interrupt();// setting thread interrupt status to false for second call to printNo
				break;//if removed no change will happen thread will run as normal
			}
		}
	}
}
