package cn.lastwhisper.concurrent.juc.tools;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 交换器
 * @author lastwhisper
 */
public class ExchangerDemo {
    private static Exchanger<String> exchanger = new Exchanger<String>();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        //python
        executorService.execute(() -> {
            try {
                String python = exchanger.exchange("Hello Python");
                System.out.println("python说：" + python);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //java
        executorService.execute(() -> {
            try {
                System.out.println("python 走出来");
                TimeUnit.SECONDS.sleep(3);
                String java = exchanger.exchange("Hello Java");
                System.out.println("java说：" + java);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
