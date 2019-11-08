package cn.lastwhisper.concurrent.example.alternate;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 编写一个程序，开启 3 个线程，这三个线程的 ID 分别为
 * A、 B、 C，每个线程将自己的 ID 在屏幕上打印 10 遍，要
 * 求输出的结果必须按顺序显示。
 * 如： ABCABCABC…… 依次递归
 * 使用Lock+Condition的await+signal
 * https://blog.csdn.net/xiaokang123456kao/article/details/77331878
 * @author lastwhisper
 */
public class TestABCAlternateForLock {
    public static void main(String[] args) {
        ABCAlternateDemo ad = new ABCAlternateDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    ad.loopA(i);
                }
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    ad.loopB(i);
                }
            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    ad.loopC(i);

                    System.out.println("-----------------------------------");
                }
            }
        }, "C").start();
    }
}

class ABCAlternateDemo {
    private int nowThread = 1;
    private Lock lock = new ReentrantLock();
    //用于控制三个线程
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void loopA(int nowLoop) {
        lock.lock();
        try {
            //判断 当前线程是阻塞还是执行
            if (nowThread != 1) {
                condition1.await();
            }
            for (int i = 1; i <= 1; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + nowLoop);
            }
            //唤醒下一个线程
            nowThread = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopB(int nowLoop) {
        lock.lock();
        try {
            //判断 当前线程是阻塞还是执行
            if (nowThread != 2) {
                condition2.await();
            }
            for (int i = 1; i <= 1; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + nowLoop);
            }
            //唤醒下一个线程
            nowThread = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopC(int nowLoop) {
        lock.lock();
        try {
            //判断 当前线程是阻塞还是打印
            if (nowThread != 3) {
                condition3.await();
            }
            for (int i = 1; i <= 1; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + nowLoop);
            }
            //唤醒下一个线程
            nowThread = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
