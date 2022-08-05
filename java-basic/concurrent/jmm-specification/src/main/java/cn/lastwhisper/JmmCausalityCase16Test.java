package cn.lastwhisper;

public class JmmCausalityCase16Test {
    private static int x = 0;
    private static int r1 = 0, r2 = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i++;
            x = 0;
            r1 = 0;
            r2 = 0;
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    r1 = x;
                    x = 1;
                }
            });

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    r2 = x;
                    x = 2;
                }
            });

            t1.start();
            t2.start();
            // 阻塞主线程，直到t1执行完
            t1.join();
            // 阻塞主线程，直到t2执行完
            t2.join();
            String result = "第" + i + "次 (" + r1 + "," + r2 + "）";
            // t1/t2 => r1=0;r2=1——(0,1)
            // t2/t1 => r1=2;r2=0——(2,0)

            // x=2,r1=x(2)；x=1,r2=x(1) => r1=2;r2=1——(2,1)概率极小
            // 第23339610次 (0,1）
            if (r1 == 2 && r2 == 1) {
                System.out.println("出现指令重排: " + result);
                break;
            } else {
                System.out.println(result);
            }
        }

    }
}
