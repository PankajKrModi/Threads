import java.util.List;
import java.util.LinkedList;
class Thread1 extends Thread{
    
    public void run(){
        synchronized(String.class){
            System.out.println("Thread1 acquired lock on String");
            try{
            Thread.sleep(100);// thread1 moves to waiting state to let thread2 start its execution but thread1 does not remove lock on String, because it has not not exited synchronization block
            }catch(InterruptedException e){}
            synchronized(Object.class){
                System.out.println("Thread1 acquired lock on Object");
            }
        }
    }
}
class Thread2 extends Thread{
    
    public void run(){
        synchronized(Object.class){
            System.out.println("Thread2 acquired lock on Object");
            try{
            Thread.sleep(100);// thread2 moves to waiting state to let thread1 start its execution but thread2 does not remove lock on Object class, because it has not not exited synchronization block
            }catch(InterruptedException e){}
            synchronized(String.class){
                System.out.println("Thread2 acquired lock on String");
            }
        }
    }
}
public class Deadlock
{
	public static void main(String[] args) {
		List<Integer> sharedQue = new LinkedList<Integer>();
		Thread t1 = new Thread(new Thread1(),"Thread-1");
		Thread t2 = new Thread(new Thread2(),"Thread-2");
		t1.start();
		t2.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Both are running and deadLocked");
	}
}