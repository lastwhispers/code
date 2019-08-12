package cn.lastwhisper.offer.Interview_2;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 懒汉式之CAS
 * @author lastwhisper
 */
public class Singleton6 {

    private static final AtomicReference<Singleton6> INSTANCE = new AtomicReference<Singleton6>();

    private Singleton6() {

    }

    public static Singleton6 getInstance() {
        for (; ; ) {
            Singleton6 singleton = INSTANCE.get();
            if (singleton != null) {
                return singleton;
            }
            singleton = new Singleton6();
            if (INSTANCE.compareAndSet(null, singleton)) {
                return singleton;
            }
        }
    }
}
