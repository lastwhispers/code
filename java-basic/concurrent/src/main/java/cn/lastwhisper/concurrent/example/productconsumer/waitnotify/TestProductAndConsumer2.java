package cn.lastwhisper.concurrent.example.productconsumer.waitnotify;

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
 *      1.3解决：除去else
 *  2.有问题版本2：spurious wakeups 虚假唤醒（JDK API在Object的wait方法中有提到）
 *      2.1问题产生原因：
 *          此时状态：多个线程同时消费、同时生产时；
 *          （1）两个消费者线程同时进入sale()被wait；
 *          （2）一个生产者线程进入get()，一次++，然后执行notifyAll()，
 *              将两个消费者线程都释放了，两次--
 *      2.2解决：使用while代替else
 *  @author lastwhisper
 */
public class TestProductAndConsumer2 {
    public static void main(String[] args) {
        Clerk2 clerk = new Clerk2();

        new Thread(new Product2(clerk), "生产者A").start();
        new Thread(new Consumer2(clerk), "消费者B").start();
        new Thread(new Product2(clerk), "生产者C").start();
        new Thread(new Consumer2(clerk), "消费者D").start();
    }
}

//店员
class Clerk2 {
    private int product = 0;
    //进货（生产者线程调用）
    public synchronized void get() {
        while (product >= 1) {
//            System.out.println("货物已满！");
            try {
                //货物满了之后生产者等待
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "添加商品，剩余数量" + ++product);
        //生产货物之后唤醒消费者
        this.notifyAll();
    }

    //销售（消费者线程调用）
    public synchronized void sale() {
        while (product <= 0) {
//            System.out.println("货物为空！");
            try {
                //货物为空之后消费者等待
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "销售商品，剩余数量" + --product);
        //消费货物之后唤醒生产者
        this.notifyAll();

    }
}


//生产者
class Product2 implements Runnable {
    private Clerk2 clerk;

    public Product2(Clerk2 clerk) {
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
class Consumer2 implements Runnable {
    private Clerk2 clerk;

    public Consumer2(Clerk2 clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.sale();
        }
    }
}
