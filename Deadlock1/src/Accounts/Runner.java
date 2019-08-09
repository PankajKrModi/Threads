package Accounts;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

	private Accounts acc1 = new Accounts();
	private Accounts acc2 = new Accounts();
	
	Lock lock1 = new ReentrantLock();
	Lock lock2 = new ReentrantLock();
	

	public void acquireLock(Lock firstLock,Lock secondLock) throws InterruptedException {
		
		while(true) {
		
			boolean gotFirstLock=false;
			boolean gotSecondLock=false;
			
			try {
				gotFirstLock = firstLock.tryLock();
				gotSecondLock = secondLock.tryLock();
			}finally {
				if(gotFirstLock && gotSecondLock) {
					return;
				}
				if(gotFirstLock) firstLock.unlock();
				if(gotSecondLock) secondLock.unlock();
			}
			
			Thread.sleep(1);
		}
	}
	public void firstThread() throws InterruptedException{
		Random random = new Random();
		
		for (int i = 0; i < 1000; i++) {
			
			acquireLock(lock1, lock2);//deadlock situation
			
			try {
			Accounts.transfer(acc1, acc2, random.nextInt(100));
			}finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}
	public void secondThread() throws InterruptedException{
		Random random = new Random();
		
		for (int i = 0; i < 1000; i++) {
			
			acquireLock(lock1, lock2);//deadlock situation
			
			try {
			Accounts.transfer(acc2, acc1, random.nextInt(100));
			}finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}
	public void finished() {
		System.out.println("Account1 balance: "+acc1.getBalance());
		System.out.println("Account2 balance: "+acc2.getBalance());
		System.out.println("Total balance: "+(acc1.getBalance()+acc2.getBalance()));
	}
}
