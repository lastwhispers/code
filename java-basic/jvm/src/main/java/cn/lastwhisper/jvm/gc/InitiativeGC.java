package cn.lastwhisper.jvm.gc;

import java.lang.ref.WeakReference;

/**
 * 主动触发GC
 * @author lastwhisper
 * @date 2020/5/14
 */
public class InitiativeGC {
    /**
     * @see cn.lastwhisper.jvm.gc.reference.WeakReferenceDemo
     */
    public static void main(String[] args) {
        Object obj = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(obj);

        System.out.println(weakReference.get());
        System.out.println("GC before,java heap " + (weakReference.get() == null ? "not exist bytes" : "exist bytes"));

        obj = null;
        System.gc();

        System.out.println(weakReference.get());
        System.out.println("GC before,java heap " + (weakReference.get() == null ? "not exist bytes" : "exist bytes"));

    }

}
