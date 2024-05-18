package ForkJoin;

import java.util.concurrent.ForkJoinPool;

public class ForkJoin {
    public static void main(String[] args) {
        int[] tasks = new int[100];
        ForkJoinPool executor = ForkJoinPool.commonPool();
        EssayTask submitTask = new EssayTask(tasks,0,tasks.length);
        System.out.println(executor.invoke(submitTask));
    }
}
