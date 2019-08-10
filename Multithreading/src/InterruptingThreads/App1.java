package InterruptingThreads;

public class App1 {

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(onInterruprionExitstheThread());
		t1.start();
		Thread.sleep(3000);
		t1.interrupt();	
		t1.join(1000);
		//Thread.join(1000);
	}
	private static Runnable onInterruprionExitstheThread() {
		return ()->{
			for(int i=0;i<9;i++) {
				System.out.println(i);
				try {
					Thread.sleep(1000);// on interrupting  a thread , 
					//no change happens to thread until it is in waiting state so Thread.sleep

				} catch (InterruptedException e) {
					System.out.println("Interrupted!");
					break;
				}
			}
		};
	}

}
