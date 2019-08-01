class RunnableInstance implements Runnable
{
	public void run(){
	    System.out.println("in run method");
	        for(int i=0;i<5;i++){
                 try {
                        Thread.sleep(500);
                  } catch (InterruptedException e) {
                        e.printStackTrace();
                  }
	            System.out.println(i+": "+Thread.currentThread().getName()+" is executing");
	        }    
	    
	}
}
public class Main{
    public static void main(String args[]) throws InterruptedException{
        RunnableInstance r1 = new RunnableInstance();
        Thread t1 = new Thread(r1,"Thread_1");
        Thread t2 = new Thread(r1,"Thread_2");
        t1.start();
        t1.join(1000);
        System.out.println("in main()");
        t2.start();
        t1.join();
        System.out.println("Exiting main thread");
    }
}
