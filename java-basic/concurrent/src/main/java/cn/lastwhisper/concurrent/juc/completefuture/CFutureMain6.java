package cn.lastwhisper.concurrent.juc.completefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 完成普通future的工作
 * 
 * thenCombine 合并结果
 * 
 * @author Geym
 *
 */
public class CFutureMain6 {

	public static Integer calc(Integer para) {
		return para / 2;
	}

	public static void main(String[] args) throws InterruptedException,ExecutionException {
		CompletableFuture<Integer> intFuture = CompletableFuture.supplyAsync(() -> calc(50));
		CompletableFuture<Integer> intFuture2 = CompletableFuture.supplyAsync(() -> calc(25));

		CompletableFuture<Void> fu = intFuture.thenCombine(intFuture2, (i, j) -> (i + j))
				.thenApply((str) -> "\"" + str + "\"")
				.thenAccept(System.out::println);
		fu.get();
	}

}
