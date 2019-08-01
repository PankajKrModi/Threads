import java.util.LinkedList;
import java.util.List;
interface CustomBlockedQueue<E>{
    void put(E obj) throws InterruptedException;
    E take() throws InterruptedException;
}
class  CustomBlockedLinkedQueue<E> implements CustomBlockedQueue<E>{
    private int maxSize=0;
    private List<E> blockedQueue ;
    
    CustomBlockedLinkedQueue(int sz){
        maxSize = sz;
        blockedQueue = new LinkedList<E>();
    }
    
    public synchronized void put(E ele) throws InterruptedException{
       if(blockedQueue.size()==maxSize){
           this.wait();
       }
       blockedQueue.add(ele);
       this.notifyAll();
    }
    
    public synchronized E take() throws InterruptedException{
        if(blockedQueue.size()==0){
            this.wait();
        }
        this.notifyAll();
        return blockedQueue.remove(0);
    }
}
public class BlockedQueueCustomImpl
{
	public static void main(String[] args) {
		CustomBlockedQueue<Integer> blockedQue = new CustomBlockedLinkedQueue<>(2);
		try{
		    blockedQue.put(1);
		    blockedQue.put(2);
		    System.out.println("elements in queue :"+blockedQue);
		    System.out.println(blockedQue.take());
		    System.out.println(blockedQue.take());
		    System.out.println(blockedQue.take());
		}catch(InterruptedException e){
		    System.out.println("Exception occured");
		}
	}
}
