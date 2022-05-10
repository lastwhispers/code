package cn.lastwhisper.concurrent.juc.future;

import java.util.concurrent.*;

/**
 * @author cunchang
 * @date 2022/4/22 5:18 PM
 */
public class FutureMain {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> futureTask = new FutureTask<>(new RealData("a"));
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        // 执行FutureTask,相当于上例中的client. request ("a")发送请求
        // 在这里开启线程进行RealData的call ()方法执行
        threadPool.submit(futureTask);
        System.out.println("异步提交任务");

        // 同步执行其他业务
        Thread.sleep(200);
        System.out.println("同步执行其他业务");

        // call方法未执行完，这里会阻塞
        System.out.println("同步获取realData:"+futureTask.get());
    }
}
