package cn.lastwhisper.interview.Thread;

/**
 * wait和sleep的区别
 * Thread.sleep只会让出CPU，不会导致锁行为的改变
 * Object.wait不仅让出CPU，还会释放已经占有的同步资源锁
 * @author lastwhisper
 */
public class WaitSleepDemo {

    public static void main(String[] args) {
        final Object lock = new Object();
        // 线程A的sleep、wait对线程B的影响
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread A is waiting to get lock");
                synchronized (lock) {
                    try {
                        System.out.println("Thread A get lock");
                        Thread.sleep(200);
                        System.out.println("Thread A do wait method");
                        //lock.wait(1000);
                        Thread.sleep(1000);
                        System.out.println("Thread A is done");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread B is waiting to get lock");
                synchronized (lock) {
                    try {
                        System.out.println("Thread B get lock");
                        System.out.println("Thread B do wait method");
                        //Thread.sleep(100);
                        lock.wait(100);
                        System.out.println("Thread B is done");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
