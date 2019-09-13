package cn.lastwhisper.jvm.gc.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author lastwhisper
 */
public class PhantomReferenceDemo {

    /**
     * 虚引用
     * 虚引用用来跟踪对象被垃圾回收器回收的活动。虚引用和软引用与弱引用的区别在于虚引用必须和引用队列联合使用，
     * 当虚引用被加入到引用队列的时候，说明这个对象已经被回收，可以在所引用的对象回收之后可以采取必要的行动。
     */
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        // 必须结合引用队列
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        // 虚引用
        PhantomReference<Object> phantomReference = new PhantomReference<>(o, referenceQueue);

        System.out.println(o);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("=================");
        o = null;
        System.gc();
        Thread.sleep(500);

        System.out.println(o);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

    }

}
