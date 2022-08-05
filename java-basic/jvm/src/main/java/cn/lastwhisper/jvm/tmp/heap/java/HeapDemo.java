package cn.lastwhisper.jvm.tmp.heap.java;

/**
 * -Xms10m -Xmx10m
 *
 * @author shkstart  shkstart@126.com
 * @create 2020  16:41
 */
public class HeapDemo {
    /**
     * 一个JVM实例对应一个进程
     * 通过java/bin 下的 jvisualVM
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("start...");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end...");
    }

}
