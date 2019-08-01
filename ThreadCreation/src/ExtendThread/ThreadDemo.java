package ExtendThread;

public class ThreadDemo extends Thread{

	public static void main(String[] args) {
		ThreadDemo t1 = new ThreadDemo();
		ThreadDemo t2 = new ThreadDemo();
		t1.start();
		t2.start();

	}
	
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("Running: "+Thread.currentThread().getName()+" "+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
