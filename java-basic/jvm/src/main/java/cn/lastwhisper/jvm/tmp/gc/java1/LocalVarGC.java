package cn.lastwhisper.jvm.tmp.gc.java1;

/**
 * @author shkstart  shkstart@126.com
 * @create 2020  14:57
 */
public class LocalVarGC {

    /**
     * buffer对象并未被回收
     *
     * [GC (System.gc()) [PSYoungGen: 12236K->480K(38400K)] 12236K->10728K(125952K), 0.0085824 secs]
     * [Full GC (System.gc()) [PSYoungGen: 480K->0K(38400K)] [ParOldGen: 10248K->10598K(87552K)] 10728K->10598K(125952K), [Metaspace: 2698K->2698K(1056768K)], 0.0047348 secs]
     */
    public void localvarGC1() {
        byte[] buffer = new byte[10 * 1024 * 1024];//10MB
        System.gc();
    }

    /**
     * Young GC 将buffer对象回收
     *
     * [GC (System.gc()) [PSYoungGen: 12236K->464K(38400K)] 12236K->472K(125952K), 0.0009152 secs]
     * [Full GC (System.gc()) [PSYoungGen: 464K->0K(38400K)] [ParOldGen: 8K->358K(87552K)] 472K->358K(125952K), [Metaspace: 2698K->2698K(1056768K)], 0.0242270 secs]
     */
    public void localvarGC2() {
        byte[] buffer = new byte[10 * 1024 * 1024];
        buffer = null;
        System.gc();
    }

    /**
     * buffer对象并未被回收，虽然buffer变量声明在局部作用域中，但是在栈帧的局部变量表中还占用一个solt
     *
     * [GC (System.gc()) [PSYoungGen: 12236K->432K(38400K)] 12236K->10680K(125952K), 0.0109850 secs] [Times: user=0.01 sys=0.01, real=0.01 secs]
     * [Full GC (System.gc()) [PSYoungGen: 432K->0K(38400K)] [ParOldGen: 10248K->10598K(87552K)] 10680K->10598K(125952K), [Metaspace: 2699K->2699K(1056768K)]
     */
    public void localvarGC3() {
        {
            byte[] buffer = new byte[10 * 1024 * 1024];
        }
        System.gc();
    }

    /**
     *  Young GC 将buffer对象回收，因为buffer变量在局部变量表中占用的solt，被value变量进行复用
     *
     * [GC (System.gc()) [PSYoungGen: 12236K->448K(38400K)] 12236K->456K(125952K), 0.0017692 secs]
     * [Full GC (System.gc()) [PSYoungGen: 448K->0K(38400K)] [ParOldGen: 8K->358K(87552K)] 456K->358K(125952K), [Metaspace: 2698K->2698K(1056768K)], 0.0046063 secs]
     */
    public void localvarGC4() {
        {
            byte[] buffer = new byte[10 * 1024 * 1024];
        }
        int value = 10;
        System.gc();
    }

    /**
     * 第二次 Full GC 将buffer对象回收
     *
     * [GC (System.gc()) [PSYoungGen: 12236K->496K(38400K)] 12236K->10744K(125952K), 0.0086581 secs] [Times: user=0.01 sys=0.01, real=0.01 secs]
     * [Full GC (System.gc()) [PSYoungGen: 496K->0K(38400K)] [ParOldGen: 10248K->10598K(87552K)] 10744K->10598K(125952K), [Metaspace: 2698K->2698K(1056768K)], 0.0053879 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [GC (System.gc()) [PSYoungGen: 0K->0K(38400K)] 10598K->10598K(125952K), 0.0005652 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
     * [Full GC (System.gc()) [PSYoungGen: 0K->0K(38400K)] [ParOldGen: 10598K->358K(87552K)] 10598K->358K(125952K), [Metaspace: 2699K->2699K(1056768K)], 0.0038486 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
     *
     */
    public void localvarGC5() {
        localvarGC1();
        System.gc();
    }

    /**
     * -XX:+PrintGCDetails
     * @param args
     */
    public static void main(String[] args) {
        LocalVarGC local = new LocalVarGC();
        local.localvarGC5();
    }
}
