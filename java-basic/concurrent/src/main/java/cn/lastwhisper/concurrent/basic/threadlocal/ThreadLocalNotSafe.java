package cn.lastwhisper.concurrent.basic.threadlocal;

/**
 * @author lastwhisper
 */
public class ThreadLocalNotSafe {
    static class Container {
        int num;
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<Container> tl = new ThreadLocal<>();
        tl.set(new Container()); // 先set下ThreadLocal

        Container container = tl.get();
        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                container.num++;
            }
        };

        //Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        //t1.start();
        t2.start();
        //t1.join();
        t2.join();

        System.out.println(tl.get().num);
    }
}
