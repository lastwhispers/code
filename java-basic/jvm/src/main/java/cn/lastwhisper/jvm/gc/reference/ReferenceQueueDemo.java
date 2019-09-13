package cn.lastwhisper.jvm.gc.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
/**
 * 监控对象是否被收回
 */
public class ReferenceQueueDemo {
    public static void main(String[] args) throws InterruptedException{
        Object o = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(o,referenceQueue);
        System.out.println(o);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());// o没有被回收时，队列为空

        System.out.println("=============");
        o = null;
        System.gc();
        Thread.sleep(500);

        System.out.println(o);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());// o被回收以后，队列有值
    }
}
