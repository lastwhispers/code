package com.desgin.pattern.creational.singleton;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @desc
 *  https://mp.weixin.qq.com/s?__biz=MzI3NzE0NjcwMg==&mid=2650123793&idx=1&sn=6ee37f4c6d071da6bf64fea3a11d394f&chksm=f36bb330c41c3a266c4cc6dfc7d4a9ee506a14bb98aad7822c053c56c04a5b20452fc3a07481&mpshare=1&scene=23&srcid=#rd
 * @author lastwhisper
 *
 */
public class CASSingleton {
    private static final AtomicReference<CASSingleton> INSTANCE = new AtomicReference<CASSingleton>();

    private CASSingleton() {
    }


    public static CASSingleton getInstance() {
        for (;;) {
            CASSingleton singleton = INSTANCE.get();
            if (null != singleton) {
                return singleton;
            }

            singleton = new CASSingleton();
            if (INSTANCE.compareAndSet(null, singleton)) {
                return singleton;
            }
        }
    }
}
