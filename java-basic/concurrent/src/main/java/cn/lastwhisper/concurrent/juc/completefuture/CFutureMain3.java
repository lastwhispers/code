package cn.lastwhisper.concurrent.juc.completefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 完成普通future的工作
 * 
 * 以下几个函数可以执行（创建）一个CompletableFuture任务
 * thenApply 转换
 * thenAccept 最后处理
 * @author Geym
 *
 */
public class CFutureMain3 {
    public static Integer calc(Integer para) {
    	try {
    		// 模拟一个长时间的执行
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
        return para*para;
    }
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {       
    	CompletableFuture<Void> fu=CompletableFuture.supplyAsync(() -> calc(50))
          .thenApply((i)->Integer.toString(i))
          .thenApply((str)->"\""+str+"\"")
          .thenAccept(System.out::println);
        fu.get();
    }
}
