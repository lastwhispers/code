package cn.lastwhisper.concurrent.UncaughtExceptionHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteCaught {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        Thread thread = new Thread(new Task());
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        exec.execute(thread);
        exec.shutdown();
    }
}