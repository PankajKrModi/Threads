package sync;

/**
 * @author pankaj
 *synchronized keyword solves interleaving problem as well as caching problem
 */
public class App3 {

	private int count=0;
	public static void main(String...strings) {
		App3 app = new App3();
		app.doWork();
	}
	private synchronized void increment() {
		count++;
	}
	
	private void doWork() {
		Thread t1 =new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					increment();
				}
				System.out.println("In thread 1");
			}
		});
		Thread t2 =new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					increment();
				}
				System.out.println("In thread 2");
			}
		});

		t1.start();
		t2.start();
		
		try {
			t1.join();//blocks main thread to run but t2 along with t1 runs simultaneously
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(count);
	}
	
}

/*Output: 
 * In thread 1
In thread 2
20000
 * 
 * 
 * */
