package cn.lastwhisper.jvm.tmp.gc.java;

/**
 * -XX:+PrintGCDetails
 * 证明：java使用的不是引用计数算法
 * @author shkstart
 * @create 2020 下午 2:38
 */
public class RefCountGC {
    //这个成员属性唯一的作用就是占用一点内存
    private byte[] bigSize = new byte[5 * 1024 * 1024];//5MB

    Object reference = null;

    public static void main(String[] args) {
        RefCountGC obj1 = new RefCountGC();
        RefCountGC obj2 = new RefCountGC();

        obj1.reference = obj2;
        obj2.reference = obj1;

        obj1 = null;
        obj2 = null;
        /**
         * 一、查看gc日志，说明两个5MB对象在eden区
         *  PSYoungGen      total 38400K, used 12902K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
         *   eden space 33280K, 38% used [0x0000000795580000,0x0000000796219b90,0x0000000797600000)
         *   from space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
         *   to   space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
         * 二、System.gc()强制gc，说明循环引用被gc掉了，因为obj1、obj2没有挂在gc roots
         *  PSYoungGen      total 38400K, used 333K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
         *   eden space 33280K, 1% used [0x0000000795580000,0x00000007955d34a8,0x0000000797600000)
         *   from space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
         *   to   space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
         *
         */
        //显式的执行垃圾回收行为
        //这里发生GC，obj1和obj2能否被回收？
        System.gc();

    }
}
