package cn.lastwhisper.concurrent.basic;

import cn.lastwhisper.concurrent.basic.state.SleepUtils;

import java.util.concurrent.TimeUnit;

public class Interrupted {
    /**
     * 许多声明抛出InterruptedException的方法（例如Thread.sleep(long millis)方法）
     * 这些方法在抛出InterruptedException之前，Java虚拟机会先将该线程的中断标识位清除，
     * 然后抛出InterruptedException，此时调用isInterrupted()方法将会返回false。
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // sleepThread不停的尝试睡眠
        Thread sleepThread = new Thread(new SleepRunner(),
                "SleepThread");
        sleepThread.setDaemon(true);
        // busyThread不停的运行
        Thread busyThread = new Thread(new BusyRunner(),
                "BusyThread");
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();
        // 休眠5秒，让sleepThread和busyThread充分运行
        TimeUnit.SECONDS.sleep(5);
        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
        System.out.println("BusyThread interrupted is " + busyThread.isInterrupted());
        // 防止sleepThread和busyThread立刻退出
        SleepUtils.second(2);
    }

    static class SleepRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtils.second(10);
            }
        }
    }

    static class BusyRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
            }
        }
    }

}