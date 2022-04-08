package cn.lastwhisper.lock;

import cn.lastwhisper.util.UnsafeInstance;
import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


@Slf4j
public class Juc04_Thread_Cas {
    /**
     * 当前加锁状态,记录加锁的次数
     */
    private volatile int state = 0;
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
    private static Juc04_Thread_Cas cas = new Juc04_Thread_Cas();

    public static void main(String[] args) {
        new Thread(new Worker(),"t-0").start();
        new Thread(new Worker(),"t-1").start();
        new Thread(new Worker(),"t-2").start();
        new Thread(new Worker(),"t-3").start();
        new Thread(new Worker(),"t-4").start();
    }

    static class Worker implements Runnable{

        @Override
        public void run() {
            log.info("请求:{}到达预定点,准备开始抢state:)",Thread.currentThread().getName());
            try {
                cyclicBarrier.await();
                if(cas.compareAndSwapState(0,1)){
                    log.info("当前请求:{},抢到锁!",Thread.currentThread().getName());
                }else{
                    log.info("当前请求:{},抢锁失败!",Thread.currentThread().getName());
                }
            } catch (InterruptedException|BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 原子操作
     * @param oldValue
     *        oldvalue:线程工作内存当中的值
     * @param
     *        newValue:要替换的新值
     * @return
     */
    public final boolean compareAndSwapState(int oldValue,int newValue){
        return unsafe.compareAndSwapInt(this,stateOffset,oldValue,newValue);
    }

    private static final Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();

    private static final long stateOffset;

    static {
        try {
            stateOffset = unsafe.objectFieldOffset(Juc04_Thread_Cas.class.getDeclaredField("state"));
        } catch (Exception e) {
            throw new Error();
        }
    }
}
