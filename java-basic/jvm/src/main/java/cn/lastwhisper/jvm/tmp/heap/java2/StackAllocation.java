package cn.lastwhisper.jvm.tmp.heap.java2;

/**
 * 栈上分配测试
 *
 * 关闭逃逸分析
 * -Xmx256m -Xms256m -XX:-DoEscapeAnalysis -XX:+PrintGCDetails
 * 创建对象速度慢，会GC
 *
 * 开启逃逸分析
 * -Xmx256m -Xms256m -XX:+DoEscapeAnalysis -XX:+PrintGCDetails
 * 创建对象速度快，不会GC
 *
 * @author shkstart  shkstart@126.com
 * @create 2020  10:31
 */
public class StackAllocation {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 10000000; i++) {
            alloc();
        }
        // 查看执行时间
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为： " + (end - start) + " ms");
        // 为了方便查看堆内存中对象个数，线程sleep
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    private static void alloc() {
        User user = new User();//未发生逃逸
    }

    static class User {

    }
}
