
public class PrintOddEvenUsingThread {

	private static int count=1;
	private static Object lock = new Object();
	public static void main(String[] args) {

		Thread odd = new Thread(new Runnable() {

			@Override
			public void run() {
				while(count<20) {
					synchronized (lock) {
						if((count&1)==0) {
							try {
								lock.wait();
							} catch (InterruptedException e) {
								System.out.println("Interrupted");
							}
						}
						System.out.println(Thread.currentThread().getName()+" executing: "+count);
						count++;
						lock.notify();
					}
				}
			}
		});

		Thread even = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				while(count<20) {
					synchronized (lock) {
						if((count&1)==1) {
							try {
								lock.wait();
							} catch (InterruptedException e) {
								System.out.println("Interrupted");
							}
						}
						System.out.println(Thread.currentThread().getName()+" executing: "+count);
						count++;
					
						lock.notify();
					}
				}
			}
		});
		
		odd.start();
		even.start();
	}

}
