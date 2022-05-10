package cn.lastwhisper.concurrent.UncaughtExceptionHandler;

import java.lang.Thread.UncaughtExceptionHandler;

public class WitchCaughtThread {
    public static void main(String args[]) {
        Thread thread = new Thread(new Task());
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();
    }
}

class ExceptionHandler implements UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("==Exception: " + e.getMessage());
    }
}