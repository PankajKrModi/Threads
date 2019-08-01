package WaitAndNotify;

public class App {

	public static void main(String[] args) {
		Processor proc = new Processor();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					proc.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					proc.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		

	}

}
