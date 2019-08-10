package CallableandFuture;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<Integer> future = executor.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				Random random = new Random();
				int duration = random.nextInt(4000);
				if(duration>2000) {
					throw new IOException("Thread sleeping for more than 2 sec");
				}
				System.out.println("Starting ...");
				Thread.sleep(duration);
				System.out.println("Finished.");
				return duration;
			}
			
		});
		
		executor.shutdown();//no new tasks is run
		
		try {
			System.out.println("Result is: "+future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			IOException ex = (IOException)e.getCause();
			System.out.println(ex.getMessage());
			//System.out.println(e.getMessage());
		}
	}

}
