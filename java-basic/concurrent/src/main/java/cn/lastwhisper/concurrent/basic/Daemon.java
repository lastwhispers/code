package cn.lastwhisper.concurrent.basic;

public class Daemon {
    /**
     * Daemon线程是一种支持型线程，因为它主要被用作程序中后台调度以及支持性工作。这
     *    意味着，当一个Java虚拟机中不存在非Daemon线程的时候，Java虚拟机将会退出。可以通过调
     *    用Thread.setDaemon(true)将线程设置为Daemon线程。
     *
     * 虚拟机没有非Daemon线程，虚拟机需要退出，此时所有Daemon线程都需要立即终止，
     *  因此DaemonRunner立即终止，但是DaemonRunner中的finally块并没有执行。
     * @param args
     */
    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner(),
                "DaemonRunner");
        thread.setDaemon(true);
        thread.start();
    }

    static class DaemonRunner implements Runnable {
        @Override
        public void run() {
            try {
                SleepUtils.second(10);
            } finally {
                System.out.println("DaemonThread finally run.");
            }
        }
    }
}