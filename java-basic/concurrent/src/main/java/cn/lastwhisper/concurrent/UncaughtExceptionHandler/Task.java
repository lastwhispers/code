package cn.lastwhisper.concurrent.UncaughtExceptionHandler;

public  class Task implements Runnable {
        @Override
        public void run() {
            System.out.println(3 / 2);
            System.out.println(3 / 0);
            System.out.println(3 / 1);
        }
    }