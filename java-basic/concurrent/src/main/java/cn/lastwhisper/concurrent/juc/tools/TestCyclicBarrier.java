package cn.lastwhisper.concurrent.juc.tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *
 * @author lastwhisper
 */
public class TestCyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        CyclicBarrierDemo cbd = new CyclicBarrierDemo(cyclicBarrier);
        for (int i = 0; i < 5; i++) {
            new Thread(cbd).start();
        }
        //CyclicBarrier是可重用的
        try {
            Thread.sleep(6000);
            System.out.println("--------------------重用CyclicBarrier--------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(cbd).start();
        }
    }
}

class CyclicBarrierDemo implements Runnable {
    private CyclicBarrier cyclicBarrier;

    public CyclicBarrierDemo(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println("线程 " + Thread.currentThread().getName() + " 查询数据开始...");
        try {
            Thread.sleep(1000);
            System.out.println("线程 " + Thread.currentThread().getName() + " 查询数据结束...");
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}