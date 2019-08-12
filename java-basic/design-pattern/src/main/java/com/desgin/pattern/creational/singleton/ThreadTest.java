package com.desgin.pattern.creational.singleton;

/**
 * Create by lastwhisper on 2019/1/27
 */
public class ThreadTest {
    public static void main(String[] args) {
        System.out.println(ThreadLocalInstance.getInstance());
        System.out.println(ThreadLocalInstance.getInstance());
        System.out.println(ThreadLocalInstance.getInstance());
        System.out.println(ThreadLocalInstance.getInstance());
        System.out.println(ThreadLocalInstance.getInstance());
        System.out.println(ThreadLocalInstance.getInstance());
        System.out.println(ThreadLocalInstance.getInstance());
        System.out.println("`````````````````````");
        Thread t1 = new Thread(new T());
        Thread t2 = new Thread(new T());
        t1.start();
        t2.start();
        System.out.println("end");
    }
}
