package cn.lastwhisper.learn8.util.concurrent.executor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * Future
 * @author lastwhisper
 * @date 2020/4/18
 */
@Slf4j
public class FutureTest {

    /**
     * Callable 有返回值
     */
    @Test
    public void testCallable() throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(5);
                return "return val";
            }
        });
        log.info("do some thing...");
        futureTask.run();
        String result = futureTask.get();
        log.info("拿到值了，为 {}", result);
    }

    /**
     * Runnable 无返回值
     *  适配器
     *
     */
    @Test
    public void testRunnable() throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<String>(new Runnable() {
            @Override
            public void run() {
                log.info("{} is run", Thread.currentThread().getName());
            }
        }, null);
        futureTask.run();
        String result = futureTask.get();
        log.info("run end,result is {}", result);
    }

    @Test
    public void testGet() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        FutureTask futureTask = new FutureTask(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "我是子线程" + Thread.currentThread().getName();
            }
        });
        executor.submit(futureTask);
        String result = (String) futureTask.get();
        log.info("result is {}", result);
    }

    @Test
    public void testThreadByCallable() throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                String result = "我是子线程" + Thread.currentThread().getName();
                log.info("子线程正在运行：{}", Thread.currentThread().getName());
                return result;
            }
        });
        new Thread(futureTask).start();
        log.info("返回的结果是 {}", futureTask.get());
    }

}
