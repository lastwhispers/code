package cn.lastwhisper.interview.Thread;

/**
 * yield的作用
 * @author lastwhisper
 */
public class YieldDemo {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + i);
                    if (i == 5) {
                        Thread.yield();
                    }
                }
            }
        };
        Thread threadA = new Thread(runnable, "A");
        Thread threadB = new Thread(runnable, "B");
        threadA.start();
        threadB.start();
    }
}
