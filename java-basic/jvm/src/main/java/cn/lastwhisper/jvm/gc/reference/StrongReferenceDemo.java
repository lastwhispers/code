package cn.lastwhisper.jvm.gc.reference;


public class StrongReferenceDemo {
    /**
     * 强引用case
     * 当内存空间不足时，Java虚拟机宁愿抛出OutOfMemoryError，
     *  也不会随意回收这个对象，如果不使用这个对象时，可以设置o=null。
     */
    public static void main(String[] args) {
        //strongRef_Memory_Enough();
        strongRef_Memory_NotEnough();
    }

    private static void strongRef_Memory_NotEnough() {
        Object o = new Object();
        System.out.println("---内存充足---");
        System.out.println("原引用："+o);

        try {
            // -Xms9m -Xmx9m 新生代:老年代=1:2 3MB:6MB
            // 大对象直接如老年代
            //byte[] bytes = new byte[6*1024*1024];//6MB
            byte[] bytes = new byte[7 * 1024 * 1024];//6MB OOM内存不足，回收软引用
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            System.out.println("---OOM触发GC---");
            System.out.println("原引用："+o);
        }

    }

    private static void strongRef_Memory_Enough() {
        Object obj1 = new Object();//这样定义默认的就是强引用
        Object obj2 = obj1;//obj2引用赋值
        obj1 = null;
        System.gc();
        System.out.println(obj2);
    }
}
