package ImplementRunnable;

class ThreadDemo implements Runnable{
	
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("Running: "+Thread.currentThread().getName()+" "+i);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class ThreadDemo1 {
	public static void main(String...args) {
		Thread t1 =new Thread(new ThreadDemo());
		Thread t2 =new Thread(new ThreadDemo());
		t1.start();
		t2.start();
	}
}
