package Solution1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {

	Random random = new Random();
	List<Integer> list1 = new ArrayList<Integer>();
	List<Integer> list2 = new ArrayList<Integer>();

	private Object lock1 = new Object();
	private Object lock2 = new Object();
	
	private void process() {
		for (int i = 0; i < 1000; i++) {
			stageOne();
			stageTwo();
		}
		
	}
	private  void stageTwo() {//this acquires lock on lock1 object 
		synchronized(lock1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			list2.add(random.nextInt(100));
		}
	}
	private  void stageOne() {//this acquires lock on lock2 object 
		synchronized(lock2) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			list1.add(random.nextInt(100));
		}
	}
	public void main() {
		long start = System.currentTimeMillis();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				process();
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				process();
			}
		});
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("Time taken: "+(end-start));
		System.out.println("list1 size: "+list1.size());
		System.out.println("list1 size: "+list2.size());
	}
}
