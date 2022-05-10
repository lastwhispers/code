package cn.lastwhisper.concurrent.UncaughtExceptionHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteCaught2 {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new ThreadPoolTask());
        exec.shutdown();
    }
}

class ThreadPoolTask implements Runnable {
    @Override
    public void run() {
        Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
        System.out.println(3 / 2);
        System.out.println(3 / 0);
        System.out.println(3 / 1);
    }
}