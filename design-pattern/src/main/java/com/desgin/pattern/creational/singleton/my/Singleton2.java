package com.desgin.pattern.creational.singleton.my;

/**
 * 懒汉式：同步锁
 * @author lastwhisper
 */
public class Singleton2 {
    private static Singleton2 instance = null;

    private Singleton2() {

    }

    public static synchronized Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }

}
