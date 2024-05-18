package ForkJoin;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadLocalRandom;

public class EssayTask extends RecursiveTask<Long> {

    private static final int THRESHOLD = 10;
    private int[] tasks;
    private int start,end;

    @Override
    protected Long compute() { //same as run , this method will be executed in work pool by each thread
        long finishedSubTopics = 0;
        if(end-start<THRESHOLD){ //base condition as it is recursive operation
            for (int i = start; i < end; i++) {
                //work on sub section
                processing();
                finishedSubTopics++;
                System.out.println("Subsection "+(i+1)+" completed by: "+Thread.currentThread().getName());
            }
            return finishedSubTopics;
        }else{
            int mid = end-(end-start)/2;
            EssayTask leftTasks = new EssayTask(tasks,start,mid);
            EssayTask rightTasks = new EssayTask(tasks,mid,end);
            leftTasks.fork(); // submit these task in forkjoin pool for async operation , submitted task in work pool
            rightTasks.fork();

            return leftTasks.join() + rightTasks.join();
        }
    }

    public EssayTask(int[] tasks, int start, int end) {
        this.tasks = tasks;
        this.start = start;
        this.end = end;
    }

    private void processing(){
        try{
            Thread.sleep(ThreadLocalRandom.current().nextInt(100,1000));
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }



}
