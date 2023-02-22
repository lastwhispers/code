package cn.lastwhisper.concurrent.juc.future;

import java.util.concurrent.*;

/**
 * @author cunchang
 * @date 2022/4/22 5:18 PM
 */
public class FutureTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> futureTask = new FutureTask<>(new Task()," circuit breaker defalut result");
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        threadPool.submit(futureTask);

        System.out.println("异步提交任务");
        // 同步执行其他业务
        Thread.sleep(200);
        System.out.println("同步执行其他业务,200ms");

        // call方法未执行完，这里会阻塞
        try {
            System.out.println("接口1000ms拿不到结果就熔断:"+futureTask.get(1, TimeUnit.SECONDS));
        } catch (TimeoutException e) {
            threadPool.shutdown();
            System.err.println(e);
        }

        threadPool.shutdown();
    }
}
