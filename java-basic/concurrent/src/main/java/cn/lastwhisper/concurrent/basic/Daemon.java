package cn.lastwhisper.concurrent.basic;

public class Daemon {
    /**
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