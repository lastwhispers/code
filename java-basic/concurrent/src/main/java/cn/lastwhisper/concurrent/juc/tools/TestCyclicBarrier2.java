package cn.lastwhisper.concurrent.juc.tools;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环栅栏：让一组线程等待至某个状态之后再全部同时执行
 * @author lastwhisper
 */
public class TestCyclicBarrier2 {
    public static void main(String[] args) {
        int n = 10;
        Thread[] soldiers = new Thread[n];
        boolean flag = false;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(n, new BarrierAction(flag, n));
        System.out.println("集合队伍！！！");
        for (int i = 0; i < n; i++) {
            System.out.println("士兵 " + i + " 报道");
            soldiers[i] = new Thread(new Soldier(cyclicBarrier, "士兵 " + i));
            soldiers[i].start();
            if (i == 6) {
                soldiers[i].interrupt();
            }
            //for循环还未执行完毕，最开始几个线程已经await，CyclicBarrier中的count已经开始计算
        }
    }
}

// CyclicBarrier等待完毕后执行的代码
class BarrierAction implements Runnable {
    boolean flag;
    int n;

    public BarrierAction(boolean flag, int n) {
        this.flag = flag;
        this.n = n;
    }

    @Override
    public void run() {
        System.out.println("被调用");
        if (flag) {
            System.out.println("司令:[士兵" + n + "个，任务完成！]");
        } else {
            System.out.println("司令:[士兵" + n + "个，集合完成！]");
            flag = true;
        }
    }
}

class Soldier implements Runnable {
    private String soldierName;
    private CyclicBarrier cyclic;

    public Soldier(CyclicBarrier cyclic, String soldierName) {
        this.cyclic = cyclic;
        this.soldierName = soldierName;
    }

    @Override
    public void run() {
        try {
            //等待所有士兵到齐
            //await调用dowait,在dowait中count==0时会调用command.run();
            cyclic.await();
            //士兵开始执行任务
            doWork();
            //等待所有士兵完成任务
            cyclic.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    void doWork() {
        try {
            Thread.sleep(Math.abs(new Random().nextInt() % 10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(soldierName + "：完成任务");
    }
}

