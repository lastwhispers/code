package cn.lastwhisper.concurrent.juc.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 线程实现的第三种方式实现Callable
 * @author lastwhisper
 */
public class TestCallable {
    public static void main(String[] args) {
        MyCallable callable = new MyCallable();
        //执行 Callable 方式，需要 FutureTask 实现类支持
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
        new Thread(futureTask).start();
        try {
            System.out.println("主线程阻塞等待");
            Integer result = futureTask.get();// FutureTask可以用于闭锁
            System.out.println(result);
            System.out.println("主线程拿到返回值");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            sum += i;
        }
        return sum;
    }
}