package com.desgin.pattern.creational.singleton.my;

/**
 * 懒汉式：DCL
 * @author lastwhisper
 */
public class Singleton3 {

    private static volatile Singleton3 instance=null;


    private Singleton3() {
    }

    public static Singleton3 getInstance() {
        if (instance == null) {
            synchronized (Singleton3.class) {
                if (instance == null) {
                    instance = new Singleton3();
                }
                return instance;
            }
        }
        return instance;
    }
}
