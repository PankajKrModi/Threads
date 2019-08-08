import java.util.LinkedList;
import java.util.List;
class Thread11 extends Thread{
    
    public void run(){
        synchronized(String.class){
            System.out.println("Thread1 acquired lock on String");
            try{
            Thread.sleep(100);
            }catch(InterruptedException e){}
            synchronized(Object.class){
                System.out.println("Thread1 acquired lock on Object");
            }
        }
    }
}
class Thread22 extends Thread{
    
    public void run(){
        synchronized(Object.class){
            System.out.println("Thread2 acquired lock on Object");
            try{
            Thread.sleep(100);
            }catch(InterruptedException e){}
            synchronized(String.class){
                System.out.println("Thread2 acquired lock on String");
            }
        }
    }
}
public class DeadlockSolve
{
	public static void main(String[] args) {
		List<Integer> sharedQue = new LinkedList<Integer>();
		Thread t1 = new Thread(new Thread11(),"Thread-1");
		Thread t2 = new Thread(new Thread22(),"Thread-2");
		t1.start();
		try{
		t1.join();// Main thread not allowed to execute until t1 completes its execution
	   }catch(InterruptedException e){}
		t2.start();
		
	}
}