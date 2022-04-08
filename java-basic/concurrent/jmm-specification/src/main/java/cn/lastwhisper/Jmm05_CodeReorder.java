package cn.lastwhisper;

public class Jmm05_CodeReorder {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    a = 1;
                    x = b;
                }
            });

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;
                    y = a;
                }
            });

            t1.start();
            t2.start();
            // 阻塞主线程，直到t1执行完
            t1.join();
            // 阻塞主线程，直到t2执行完
            t2.join();
            // 第12627次 (0,0）
            // 第951620次 (0,0）
            // 出现指令重排: 第22648505次 (0,0）
            String result = "第" + i + "次 (" + x + "," + y + "）";
            if (x == 0 && y == 0) {
                System.out.println("出现指令重排: " + result);
                break;
            } else {
                System.out.println(result);
            }
        }

    }
}
