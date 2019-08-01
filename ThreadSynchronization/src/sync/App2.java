package sync;

/**
 * @author pankaj
 *We can synchronize threads using Thread.sleep() or t.join()
 *volatile keyword solves caching problem not interleaving problem 
 *becoz of interleaving issue count is never 20000
 *mutex also called monitor lock of object
 */
public class App2 {

	private volatile int count=0;
	public static void main(String...strings) {
		App2 app = new App2();
		app.doWork();
	}
	
	private void doWork() {
		Thread t1 =new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					count++;
				}
				System.out.println("In thread 1");
			}
		});
		Thread t2 =new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					count++;
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
 * In thread 2
In thread 1
14053
 * Or
 * In thread 1
In thread 2
10513
 * 
 * 
 * */
