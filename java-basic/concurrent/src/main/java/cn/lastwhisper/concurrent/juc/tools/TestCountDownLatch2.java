package cn.lastwhisper.concurrent.juc.tools;

import java.time.LocalDate;
import java.time.Period;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 范围日期，按天提交到线程池，最后等待汇总
 * @author lastwhisper
 */
public class TestCountDownLatch2 {

    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        LocalDate startDate = LocalDate.now().plusDays(-30), endDate = LocalDate.now();

        // 这种才能计算出日期之差
        int dayGap = (int) (endDate.toEpochDay() - startDate.toEpochDay())+1;
        // 这种要同月才行
//        int dayGap = Period.between(endDate,startDate).getDays()+1;
        System.out.println("startDate:" + startDate + " endDate:" + endDate + " 日期之差:" + dayGap);

        CountDownLatch countDownLatch = new CountDownLatch(dayGap);

        while (!startDate.isAfter(endDate)) {
            LocalDate finalStartDate = startDate;
            threadPool.submit(() -> {
                try {
                    Thread.sleep(100);
                    System.out.println("startDate:" + finalStartDate);
                } catch (InterruptedException e) {
                    System.out.println("startDate:" + finalStartDate+"InterruptedException:"+e);
                    Thread.interrupted();
                } finally {
                    countDownLatch.countDown();
                }
            });
            startDate = startDate.plusDays(1L);
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 关闭线程池，立即
        threadPool.shutdownNow();
    }
}
