
package cn.lastwhisper.concurrent.basic.Interrupted;

public class InterruptSynchronizedDemo {
    private static final Object lock = new Object();//monitor
    private static class BlockedThread extends Thread {
        @Override
        public void run() {
        	//等待lock锁
            synchronized (lock) {
            		//等待标志位被置为true
                while (!Thread.currentThread().isInterrupted()) {
                }
            }
            System.out.println("exit");
        }
    }
    public static void test() throws InterruptedException {
        synchronized (lock) {//获取锁
            BlockedThread blockedThread = new BlockedThread();
            blockedThread.start();
            Thread.sleep(1000);
			//blockedThread在等待lock锁，interrupt 无法中断
            blockedThread.interrupt();
            //blockedThread线程加入当前线程，main线程等待blockedThread执行完毕，才会继续往下执行
            blockedThread.join();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        test();
    }
}