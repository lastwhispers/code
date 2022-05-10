package cn.lastwhisper.concurrent.juc.lockfree;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 一、i++的原子性问题：i++并非原子操作，取值,修改,存储
 * int temp = i;//第一步：取值
 * i = i + 1;//第二步：修改
 * i = temp;//第三步：存储
 * 内存到寄存器
 * 寄存器自增
 * 写回内存
 * 二、原子变量：jdk5后java.util.concurrent.atomic 包下提供了常用的原子变量
 * AtomicInteger
 * 1.volatile 保证可见性
 * 2.CAS（Compare-And-Swap） 保证原子性
 * CAS 算法是硬件对于并发操作共享数据的支持
 * CAS 算法的过程：包含三个参数CAS(V,E,N)，
 * V 表示要更新的变量
 * E 表示预期值
 * N 表示新值
 * 仅当V==E时，才会将V=N，如果V!=E说明有其他线程做了更新，则当前线程什么也不做。
 *
 * @author lastwhisper
 */
public class AtomicIntegerTest {
    public static void main(String[] args) {
        AtomicDemo ad = new AtomicDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(ad).start();
        }
    }
}

class AtomicDemo implements Runnable {
    private int i;
    //private volatile int i;
    private AtomicInteger serialNum = new AtomicInteger();

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " : " + increment());
    }

    public int increment() {
        //return serialNum.incrementAndGet();
        return i++;
    }
}
