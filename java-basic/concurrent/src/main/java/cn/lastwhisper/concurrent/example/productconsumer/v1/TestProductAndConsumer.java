package cn.lastwhisper.concurrent.example.productconsumer.v1;

/**
 * 生产者消费者案例(等待唤醒机制)，使用synchronize+Object的wait和notify；
 *  1.有问题版本1：等待了唤醒不了
 *      1.1前置知识：sleep放弃CPU执行权，不释放锁；wait释放CPU执行权，释放锁
 *      1.2问题产生原因：
 *          此时状态：product=0；消费者线程剩余循环次数=1；生产者线程剩余循环次数=2
 *          （1）.消费者线程拿到锁执行sale()到this.wait()；
 *          （2）.生产者线程拿到锁执行get()，此时product=1；生产者线程剩余循环次数=1
 *              同时 this.notifyAll()唤醒消费者线程；
 *          （3）.消费者线程接着this.wait()之后执行，此时product=1；消费者线程剩余循环次数=0；---消费者线程不会再执行
 *          （4）.生产者线程拿到锁执行get()，此时product=1，执行this.wait()；---没有线程唤醒生产者线程
 * @author lastwhisper
 */
public class TestProductAndConsumer {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        new Thread(new Product(clerk), "生产者").start();
        new Thread(new Consumer(clerk), "消费者").start();
    }
}

//店员
class Clerk {
    private int product = 0;

    //进货（生产者线程调用）
    public synchronized void get() {//循环次数：2——》1
        if (product >= 1) {
            System.out.println("货物已满！");
            try {
                //货物满了之后生产者等待
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " : " + ++product);
            //生产货物之后唤醒消费者
            this.notifyAll();
        }
    }

    //销售（消费者线程调用）
    public synchronized void sale() {//product：0——》1，循环次数：1——》0
        if (product <= 0) {
            System.out.println("货物为空！");
            try {
                //货物为空之后消费者等待
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " : " + --product);
            //消费货物之后唤醒生产者
            this.notifyAll();
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
