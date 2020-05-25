package cn.lastwhisper.jvm.gc.gcroots;

/**
 * 引用计数法的缺陷
 * @author lastwhisper
 */
public class ReferenceCountingGC {

    public Object install = null;
    
    private static final int _1MB = 1024 * 1024;

    /**
     * 这个成员属性的唯一意义就是占点内存， 以便能在GC日志中看清楚是否有回收过
     */
    private byte[] bigSize = new byte[2*_1MB];

    /**
     * -XX:+PrintGC //等价 -verbose:gc
     * -XX:+PrintGCDetails
     * -XX:+PrintGCDateStamps
     * -Xloggc:C:\Users\ligj\Downloads\gc.log
     *
     * 这里使用 -XX:+PrintGCDetails
     */
    public static void main(String[] args){
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.install=objB;
        objB.install=objA;

        objA=null;
        objB=null;

        // 假设在这行发生GC， objA和objB是否能被回收？
        // 可以被回收
        System.gc();
    }
}
