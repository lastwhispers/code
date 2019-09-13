package cn.lastwhisper.jvm.gc.reference;

import java.lang.ref.WeakReference;


public class WeakReferenceDemo {
    /**
     * 弱引用case
     * 当发生GC工作时，无论当前内存是否够用，都会立即清除弱引用对象。
     */
    public static void main(String[] args) {
        Object o = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o);
        System.out.println("---内存充足---");
        System.out.println("原引用："+o);
        System.out.println("弱引用："+weakReference.get());

        o = null;
        System.gc();
        System.out.println("----原引用置为null，并开始GC---");

        System.out.println("原引用："+o);
        System.out.println("弱引用："+weakReference.get());
    }

}
