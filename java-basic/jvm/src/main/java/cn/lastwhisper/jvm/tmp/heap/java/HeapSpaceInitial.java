package cn.lastwhisper.jvm.tmp.heap.java;

/**
 * 1. 设置堆空间大小的参数
 * -Xms 用来设置堆空间（年轻代+老年代）的初始内存大小
 * -X 是jvm的运行参数
 * ms 是memory start
 * -Xmx 用来设置堆空间（年轻代+老年代）的最大内存大小
 * <p>
 * 2. 默认堆空间的大小
 * 初始内存大小：物理电脑内存大小 / 64
 * 最大内存大小：物理电脑内存大小 / 4
 * 3. 手动设置：-Xms600m -Xmx600m
 * 开发中建议将初始堆内存和最大的堆内存设置成相同的值。
 * <p>
 * 4. 查看设置的参数：方式一： jps   /  jstat -gc 进程id
 * 方式二：-XX:+PrintGCDetails
 *
 * @author shkstart  shkstart@126.com
 * @create 2020  20:15
 */
public class HeapSpaceInitial {

    /**
     * 设置 -Xms600m -Xmx600m
     * 输出 -Xms : 575M -Xmx : 575M
     * <p>
     * Q：为什么设置600，可用只有575？
     * A：方式一： jps   /  jstat -gc 进程id
     *
     * S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
     * 25600.0 25600.0  0.0    0.0   153600.0 43010.4   409600.0    0.0     4480.0 780.7  384.0   76.6   0      0.000   0      0.000    0.000
     *
     * C表示count总数，U表示Used已使用，单位KB
     * <p>
     * SOC+S1C+EC+OC=25600+25600+153600+409600=614400KB/1024=600MB
     * 这600MB与我们设置的参数对上了，但是由于GC算法的原因，S0和S1只有一个可用存放对象，导致可用大小为575MB
     *
     * A：方式二：-XX:+PrintGCDetails
     *
     * Heap
     *  PSYoungGen      total 179200K, used 9216K [0x00000007b3800000, 0x00000007c0000000, 0x00000007c0000000)
     *   eden space 153600K, 6% used [0x00000007b3800000,0x00000007b41001a0,0x00000007bce00000)
     *   from space 25600K, 0% used [0x00000007be700000,0x00000007be700000,0x00000007c0000000)
     *   to   space 25600K, 0% used [0x00000007bce00000,0x00000007bce00000,0x00000007be700000)
     *  ParOldGen       total 409600K, used 0K [0x000000079a800000, 0x00000007b3800000, 0x00000007b3800000)
     *   object space 409600K, 0% used [0x000000079a800000,0x000000079a800000,0x00000007b3800000)
     *  Metaspace       used 3212K, capacity 4496K, committed 4864K, reserved 1056768K
     *   class space    used 352K, capacity 388K, committed 512K, reserved 1048576K
     *
     * PSYoungGen+ParOldGen=179200K+409600K=588800K/1024=575MB
     *
     *
     */


    public static void main(String[] args) {

        //返回Java虚拟机中的堆内存总量
        long initialMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        //返回Java虚拟机试图使用的最大堆内存量
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;

        /**
         * 默认值
         * -Xms : 123M
         * -Xmx : 1820M
         * 系统内存大小为：7.6875G
         * 系统内存大小为：7.109375G
         *
         * 系统内存计算不足8G的原因：
         * 一、新生代eden区的S0和S1只有一个可以存放对象
         * 二、os本身占用部分
         *
         */
        System.out.println("-Xms : " + initialMemory + "M");
        System.out.println("-Xmx : " + maxMemory + "M");
//        System.out.println("系统内存大小为：" + initialMemory * 64.0 / 1024 + "G");
//        System.out.println("系统内存大小为：" + maxMemory * 4.0 / 1024 + "G");
//
//        try {
//            Thread.sleep(1000000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
