package cn.lastwhisper.lock;

import java.util.concurrent.locks.ReentrantLock;


public class Juc04_Thread_ReentrantLock {
    /**
     * 可重入锁，怎么实现类似于synchronized的功能
     */
    public static ReentrantLock lock = new ReentrantLock(true);

    static boolean flag = false;

    public static void main(String[] args) {

        for (int i=0;i<10;i++){
            new Thread(()->{
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"get lock");
                    while (!flag){
                        if(flag){
                            break;
                        }
                    }
                lock.unlock();
            },"t-"+i).start();
        }



    }

}
