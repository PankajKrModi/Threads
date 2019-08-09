package Semaphores;

import java.util.concurrent.Semaphore;

public class Connection {

	private static Connection instance = new Connection(); 
	
	Semaphore sem = new Semaphore(10,true);
	
	private int connections =0;
	
	//making it singleton
	private Connection() {
		
	}
	
	public static Connection getInstance() {
		return instance;
	}
	
	public void connect() {
		try {
			sem.acquire();
			doConnect();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			sem.release();
		}
		
	}
	private void doConnect() {
		
		
		synchronized(this) {
			connections++;
			System.out.println("Current connections: "+connections);
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		synchronized (this) {
			connections--;
		}
		
	}
}
