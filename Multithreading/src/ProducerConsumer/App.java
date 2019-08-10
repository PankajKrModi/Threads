package ProducerConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {

	public static void main(String[] args) {
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
		new Thread(new Producer(queue)).start();
		new Thread(new Consumer(queue)).start();

	}

}

class Producer implements Runnable{

	BlockingQueue<Integer> queue ;
	
	@Override
	public void run() {
		for(int i=0;i<20;i++) {
			try {
				queue.put(i);
				System.out.println("Value produced: "+i);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
	public Producer(BlockingQueue<Integer> q) {
		this.queue =q;
	}
	
}
class Consumer implements Runnable{

	BlockingQueue<Integer> queue ;
	
	@Override
	public void run() {
		while(true) {
			try {
				Integer val = queue.take();
				System.out.println("Value removed: "+val);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Consumer(BlockingQueue<Integer> q) {
		this.queue =q;
	}	
}