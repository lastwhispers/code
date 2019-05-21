package com.desgin.pattern.creational.singleton;

/**
 * Create by eval on 2019/1/25
 */
public class T implements Runnable {
    @Override
    public void run() {
//        LazySingleton instance = LazySingleton.getInstance();
//        LazyDoubleCheckSingleton instance = LazyDoubleCheckSingleton.getInstance();
//        StaticInnerClassSingleton instance = StaticInnerClassSingleton.getInstance();
        ThreadLocalInstance instance = ThreadLocalInstance.getInstance();
        System.out.println(Thread.currentThread().getName() + "  " + instance);
    }
}
