package sync;

/**
 * @author pankaj
 *We can synchronize threads using Thread.sleep() or t.join()
 */
public class App1 {

	private int count=0;
	public static void main(String...strings) {
		App1 app = new App1();
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
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t2.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(count);
	}
	
}
