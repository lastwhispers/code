package cn.lastwhisper.interview.Thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 处理线程的返回值：
 * 通过Callable接口实现：线程池
 * @author lastwhisper
 */
public class ThreadPoolDemo {
    public static void main(String[] args){
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        Future<String> future = cachedThreadPool.submit(new MyCallable());
        if(!future.isDone()){
            System.out.println("task is alive");
        }
        try {
            System.out.println(future.get());
            System.out.println("task is dead");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            cachedThreadPool.shutdown();
        }
    }
}
