package sync;

import java.util.Scanner;

class Processor extends Thread{
	private volatile boolean running =true;
	@Override
	public void run() {
		while(running) {
			System.out.println("Hello");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public void shutdown() {
		running =false;
	}
}
public class App {

	public static void main(String[] args) {
		Processor p1 = new Processor();
		p1.start();
		System.out.println("Press return to stop ...");
		Scanner in = new Scanner(System.in);
		in.nextLine();
		p1.shutdown();
	}

}
