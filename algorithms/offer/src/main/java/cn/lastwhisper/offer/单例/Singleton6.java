package cn.lastwhisper.offer.单例;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 懒汉式之CAS
 * @author cn.lastwhisper
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
