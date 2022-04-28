package cn.lastwhisper.concurrent.basic.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author cunchang
 * @date 2022/4/28 7:54 PM
 */
public class InheritableThreadLocalDemo {
    public static void main(String[] args) throws InterruptedException {
        final InheritableThreadLocal<Span> inheritableThreadLocal = new InheritableThreadLocal<Span>();
        inheritableThreadLocal.set(new Span("xiexiexie"));
        //输出 xiexiexie
        Object o = inheritableThreadLocal.get();
        System.out.println("main get Span:"+o);
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("========");
                Object o =  inheritableThreadLocal.get();
                System.out.println("t get Span:"+o);
                inheritableThreadLocal.set(new Span("zhangzhangzhang"));
                o = inheritableThreadLocal.get();
                System.out.println("t update Span:"+o);
            }
        };
        thread.start();
        thread.join();
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
