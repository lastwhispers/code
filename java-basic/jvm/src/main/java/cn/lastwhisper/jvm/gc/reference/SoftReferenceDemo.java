package cn.lastwhisper.jvm.gc.reference;

import java.lang.ref.SoftReference;


public class SoftReferenceDemo {

    /**
     * 软引用case
     * 内存够用的时候就保留软引用，不够用就回收软引用
     * @author lastwhisper
     */
    public static void main(String[] args) {
        //softRef_Memory_Enough();
        softRef_Memory_NotEnough();
    }

    public static void softRef_Memory_Enough() {
        Object o = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o);

        System.out.println("---内存充足---");
        System.out.println("原引用："+o);
        System.out.println("软引用："+softReference.get());

        o = null;
        System.gc();
        System.out.println("----原引用置为null，并开始GC---");
        System.out.println("原引用："+o);//null
        System.out.println("软引用："+softReference.get());//java.lang.Object@4d7e1886
    }

    public static void softRef_Memory_NotEnough() {
        Object o = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o);

        System.out.println("---内存充足---");
        System.out.println("原引用："+o);
        System.out.println("软引用："+softReference.get());

        o = null;
        try {
            // -Xms9m -Xmx9m 新生代:老年代=1:2 3MB:6MB
            // 大对象直接如老年代
            //byte[] bytes = new byte[6*1024*1024];//6MB 内存足够，不会回收软引用
            byte[] bytes = new byte[7 * 1024 * 1024];//7MB OOM触发GC，回收软引用
        } catch (Throwable e) {
            System.out.println("---原引用置为null， OOM触发GC---");
            e.printStackTrace();
            // java.lang.OutOfMemoryError: Java heap space
            //	at cn.lastwhisper.jvm.gc.reference.SoftReferenceDemo.softRef_Memory_NotEnough(SoftReferenceDemo.java:46)
            //	at cn.lastwhisper.jvm.gc.reference.SoftReferenceDemo.main(SoftReferenceDemo.java:15)
        } finally {

            System.out.println("原引用："+o);//null
            System.out.println("软引用："+softReference.get());// null 内存不足被回收
            // 是否会被 GC ？不会因为 softReference GCRoots 可到达
            System.out.println("softReference："+softReference);
        }
    }

}
