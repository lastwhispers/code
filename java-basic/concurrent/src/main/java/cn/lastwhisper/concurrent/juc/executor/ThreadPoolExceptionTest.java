package cn.lastwhisper.concurrent.juc.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 测试线程池出现异常时的线程id
 * @author lastwhisper
 * @date 2022/5/2
 */
public class ThreadPoolExceptionTest {

    public static void main(String[] args) {
        // 线程池会复用线程，线程id会重复出现；
        // 但是线程池执行任务出现异常，会把异常线程移除，创建新的线程
        ExecutorService pool = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            pool.execute(() -> {
                if(finalI % 2==0){
                    try {
                        int x = finalI/0;
                    } catch (Exception e) {
                        System.err.println("异常线程id:"+Thread.currentThread().getId()+" e:"+e);
                        throw e;
                    }
                }
                System.err.println("无异常线程id:"+Thread.currentThread().getId());
            });
        }
        pool.shutdown();


//        ExecutorService pool = Executors.newFixedThreadPool(1);
//        for (int i = 0; i < 50; i++) {
//            int finalI = i;
//            Future<?> future = pool.submit(() -> {
//                if (finalI % 2 == 0) {
//                    try {
//                        int x = finalI / 0;
//                    } catch (Exception e) {
//                        System.err.println("异常线程id:" + Thread.currentThread().getId() + " e:" + e);
//                        throw e;
//                    }
//                }
//                System.err.println("无异常线程id:" + Thread.currentThread().getId());
//            });
//            try {
//                System.out.println(future.get());
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
//        }
//        pool.shutdown();

    }

}
