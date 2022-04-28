package cn.lastwhisper.concurrent.basic.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author cunchang
 * @date 2022/4/28 7:54 PM
 */
public class AlibabaThreadLocalDemo {
    public static void main(String[] args) throws InterruptedException {
        final TransmittableThreadLocal<Span> context = new TransmittableThreadLocal<>();
        context.set(new Span("xiexiexie"));
        //输出 xiexiexie
        Object o = context.get();
        System.out.println("main get Span:"+o);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("========");
                Object o =  context.get();
                System.out.println("t get Span:"+o);
                context.set(new Span("zhangzhangzhang"));
                o = context.get();
                System.out.println("t update Span:"+o);
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(TtlRunnable.get(runnable));
        TimeUnit.SECONDS.sleep(1);
        executorService.submit(TtlRunnable.get(runnable));
        TimeUnit.SECONDS.sleep(1);
        System.out.println("========");
        Span span = context.get();
        System.out.println("main last again get Span:"+o);


        executorService.shutdown();
    }

    static class Span {
        public String name;
        public int age;

        public Span(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Span{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
