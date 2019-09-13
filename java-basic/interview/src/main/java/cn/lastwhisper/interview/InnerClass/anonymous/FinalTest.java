package cn.lastwhisper.interview.InnerClass.anonymous;

public class FinalTest {
    public void test(final int b) {
        final int a = 100;
        new Thread() {
            public void run() {
                //a = a + 1;
                System.out.println(a);
                System.out.println(b);
            }
        }.start();
        // 做一些耗时的操作，run()方法先执行完
        // 使用a，a变成101，本来要使用a=100的
    }
}