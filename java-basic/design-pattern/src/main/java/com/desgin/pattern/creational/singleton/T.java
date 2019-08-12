package com.desgin.pattern.creational.singleton;

/**
 * Create by lastwhisper on 2019/1/25
 */
public class T implements Runnable {
    @Override
    public void run() {
        //测试同步锁单例
//        LazySingleton instance = LazySingleton.getInstance();
        //测试双重检测锁单例
//        LazyDoubleCheckSingleton instance = LazyDoubleCheckSingleton.getInstance();
        //测试静态内部类单例
//        StaticInnerClassSingleton instance = StaticInnerClassSingleton.getInstance();
        //测试线程单例
//        ThreadLocalInstance instance = ThreadLocalInstance.getInstance();
//        System.out.println(Thread.currentThread().getName() + "  " + instance);
        //测试CAS单例
        CASSingleton instance = CASSingleton.getInstance();
        System.out.println(instance);
    }
}
