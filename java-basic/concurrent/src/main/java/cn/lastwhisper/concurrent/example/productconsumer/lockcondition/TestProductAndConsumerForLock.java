package cn.lastwhisper.concurrent.example.productconsumer.lockcondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者案例(等待唤醒机制)，使用Lock+Condition；
 * 1、线程（ new Thread）、操作（get、sale）、资源类（店员）
 * 2、判断、干货、通知
 * 3、防止虚假唤醒
 * @author lastwhisper
 */
public class TestProductAndConsumerForLock {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        new Thread(new Product(clerk), "生产者A").start();
        new Thread(new Consumer(clerk), "消费者B").start();
        new Thread(new Product(clerk), "生产者C").start();
        new Thread(new Consumer(clerk), "消费者D").start();
    }
}

//店员
class Clerk {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private int product = 0;

    //进货（生产者线程调用）
    public void get() {
        lock.lock();
        try {
            while (product >= 1) {
                System.out.println("货物已满！");
                //货物满了之后生产者等待
                //this.wait();
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + " : " + ++product);
            //生产货物之后唤醒消费者
            //this.notifyAll();
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //销售（消费者线程调用）
    public void sale() {
        lock.lock();
        try {
            while (product <= 0) {
                System.out.println("货物为空！");
                //货物为空之后消费者等待
                //this.wait();
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + " : " + --product);
            //消费货物之后唤醒生产者
            //this.notifyAll();
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}


//生产者
class Product implements Runnable {
    private Clerk clerk;

    public Product(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(200);//网络延迟
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.get();
        }
    }
}

//消费者
class Consumer implements Runnable {
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.sale();
        }
    }
}
