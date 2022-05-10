package cn.lastwhisper.concurrent.juc.completefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 完成普通future的工作
 * 
 * exceptionally 异常处理 发生异常进行处理，如果没有异常，则它返回原有的结果
 * 
 * @author Geym
 *
 */
public class CFutureMain4 {

	public static Integer calc(Integer para) {
		return para / 0;
	}

	public static void main(String[] args) throws InterruptedException,ExecutionException {
		CompletableFuture<Void> fu = CompletableFuture
				.supplyAsync(() -> calc(50))
				.exceptionally(ex -> {
					System.out.println(ex.toString());
					return 0;
				})
				.thenApply((i) -> Integer.toString(i))
				.thenApply((str) -> "\"" + str + "\"")
				.thenAccept(System.out::println);
		fu.get();
	}

}
