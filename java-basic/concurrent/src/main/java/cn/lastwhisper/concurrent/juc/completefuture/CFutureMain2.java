package cn.lastwhisper.concurrent.juc.completefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 完成普通future的工作
 * 
 * 以下几个函数可以执行（创建）一个CompletableFuture任务
 * static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier);
 * static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor);
 * static CompletableFuture<Void> runAsync(Runnable runnable);
 * static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor);
 * @author Geym
 *
 */
public class CFutureMain2 {
    public static Integer calc(Integer para) {
    	try {
    		// 模拟一个长时间的执行
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
        return para*para;
    }
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final CompletableFuture<Integer> future =
                CompletableFuture.supplyAsync(() -> calc(50));
        System.out.println(future.get());
    }
}
