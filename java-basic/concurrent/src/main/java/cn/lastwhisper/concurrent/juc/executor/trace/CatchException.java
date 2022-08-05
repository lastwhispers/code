package cn.lastwhisper.concurrent.juc.executor.trace;

import java.util.concurrent.*;

/**
 * submit吃掉异常
 * 1. 用Future.get()获得异常
 * 2. try-catch
 * @author Geym
 *
 */
public class CatchException {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadPoolExecutor pools=new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                0L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());

        // 1、不打印异常；submit
        for(int i=0;i<5;i++){
            pools.submit(new DivTask(100,i));
        }
        System.out.println("======================================================");
        // 2、打印异常；execute
        for (int i = 0; i < 5; i++) {
            pools.execute(new DivTask(100, i));
        }
        System.out.println("======================================================");
        // 3、打印异常；Future.get()
        for (int i = 0; i < 5; i++) {
            Future re = pools.submit(new DivTask(100, i));
            try {
                re.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
