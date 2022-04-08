package cn.lastwhisper.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;


@Slf4j
public class Juc01_Thread_LockSupport {

    public static void main(String[] args) {

        Thread t0 = new Thread(new Runnable() {

            @Override
            public void run() {
                Thread current = Thread.currentThread();
                log.info("{},开始执行!",current.getName());
                for(;;){//spin 自旋
                    log.info("准备park住当前线程：{}....",current.getName());
                    LockSupport.park();
                    log.info("当前线程{}已经被唤醒....",current.getName());
                }
            }

        },"t0");

        t0.start();

        try {
            Thread.sleep(5000);
            log.info("准备唤醒{}线程!",t0.getName());
            LockSupport.unpark(t0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
