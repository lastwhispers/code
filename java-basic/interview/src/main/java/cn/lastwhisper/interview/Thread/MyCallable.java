package cn.lastwhisper.interview.Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 处理线程的返回值
 * @author lastwhisper
 */
public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        //子线程执行中
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //拿到子线程计算的结果
        return "task return value";
    }

}
