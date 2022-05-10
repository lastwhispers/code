package cn.lastwhisper.concurrent.UncaughtExceptionHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InitiativeCaught {
    public void threadDeal(Runnable r, Throwable t) {
        System.out.println("==Exception: " + t.getMessage());
    }

    class InitialtiveThread implements Runnable {
        @Override
        public void run() {
            Throwable thrown = null;
            try {
                System.out.println(3 / 2);
                System.out.println(3 / 0);
                System.out.println(3 / 1);
            } catch (Throwable e) {
                thrown = e;
            } finally {
                threadDeal(this, thrown);
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new InitiativeCaught().new InitialtiveThread());
        exec.shutdown();
    }

}