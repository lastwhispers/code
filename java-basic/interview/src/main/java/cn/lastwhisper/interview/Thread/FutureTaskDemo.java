package cn.lastwhisper.interview.Thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 处理线程的返回值：
 * 通过Callable接口实现：FutureTask
 * @author lastwhisper
 */
public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<String>(new MyCallable());
        Thread thread = new Thread(futureTask);
        thread.start();
        if (!futureTask.isDone()){
            System.out.println("task is alive");
        }
        System.out.println(futureTask.get());
        System.out.println("task is dead");
    }
}
