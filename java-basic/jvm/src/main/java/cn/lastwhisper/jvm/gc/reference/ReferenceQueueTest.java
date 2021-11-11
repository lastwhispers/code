package cn.lastwhisper.jvm.gc.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 监控对象是否被收回
 */
public class ReferenceQueueTest {

    private static ReferenceQueue<byte[]> rq = new ReferenceQueue<byte[]>();
    private static int _1M = 1024*1024;
    /**
     *
     * 因为map的key是WeakReference，所以在内存不足的时候，weakReference所指向的对象就会被GC，
     * 在对象被GC的同时，会把该对象的包装类即weakReference放入到ReferenceQueue里面。
     *
     * 但是这个map的大小是100.
     *
     */
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                int cnt = 0;
                WeakReference<byte[]> k;
                while((k = (WeakReference) rq.remove()) != null) {
                    System.out.println((cnt++) + " 回收了:" + k.get());
                }
            } catch(InterruptedException e) {
                //结束循环
            }
        });
        thread.setDaemon(true);
        thread.start();

        Set<WeakReference> handlerSet = new HashSet<>();
        for(int i = 0;i < 100;i++) {
            byte[] bytes = new byte[_1M];
            WeakReference<byte[]> weakReference = new WeakReference<>(bytes, rq);
            handlerSet.add(weakReference);
        }
        System.out.println("size->" + handlerSet.size());
    }
}