package com.desgin.pattern.creational.singleton;

/**
 * Create by lastwhisper on 2019/1/27
 */
public class ThreadLocalInstance {
    private static final ThreadLocal<ThreadLocalInstance> treadLocalInstance =
            new ThreadLocal<ThreadLocalInstance>() {
                @Override
                protected ThreadLocalInstance initialValue() {
                    return new ThreadLocalInstance();
                }
            };

    private ThreadLocalInstance() {
    }

    public static ThreadLocalInstance getInstance() {
        return treadLocalInstance.get();
    }
}
