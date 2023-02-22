package cn.lastwhisper.concurrent.juc.completefuture;

import java.util.concurrent.CompletableFuture;

/**
 * 脱离线程池的使用，仅作为一个契约
 * 
 * @author Geym
 *
 */
public class CFutureMain1 {
    public static class AskThread implements Runnable {
        CompletableFuture<Integer> re = null;

        public AskThread(CompletableFuture<Integer> re) {
            this.re = re;
        }

        @Override
        public void run() {
            int myRe = 0;
            try {
                myRe = re.get() * re.get();
            } catch (Exception e) {
            }
            System.out.println(myRe);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final CompletableFuture<Integer> future = new CompletableFuture<>();
        new Thread(new AskThread(future)).start();
        // 模拟长时间其他调用
        Thread.sleep(1000);
        // 告知完成结果
        future.complete(60);
    }
}
