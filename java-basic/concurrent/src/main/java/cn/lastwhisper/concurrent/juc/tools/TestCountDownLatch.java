package cn.lastwhisper.concurrent.juc.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * 闭锁是一种同步方法，可以延迟线程的进度直到其到达终止状态。
 * 闭锁可以用来确保某些活动直到其他活动都完成才继续执行：
 *
 * @author lastwhisper
 */
public class TestCountDownLatch {
    // 线程池并行执行任务
    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        CountDownLatch latch = new CountDownLatch(5);

        List<String> months = Arrays.asList("1月份", "2月份", "3月份", "4月份", "5月份");
        List<Future<String>> futureList = new ArrayList<>();
        // 并发执行每个月的报表
        for (String month : months) {
            Future<String> future = threadPool.submit(new Report(latch, month));
            futureList.add(future);
        }
        System.out.println("等待所有线程执行完毕");
        latch.await();
        // 对每个线程执行结果进行汇总
        List<String> allReportData = new ArrayList<>();
        for (Future<String> future : futureList) {
            allReportData.add(future.get());
        }

        System.out.println(allReportData);

        // 关闭线程池
        threadPool.shutdown();
    }
}

class Report implements Callable<String> {
    private CountDownLatch latch;
    private String month;

    public Report(CountDownLatch latch, String month) {
        this.latch = latch;
        this.month = month;
    }

    @Override
    public String call() {
        try {
            Thread.sleep(200);
            System.out.println(month + "报表统计完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //线程完成后，进行减一
            latch.countDown();
        }
        return month + "报表数据";
    }


}