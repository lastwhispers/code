package com.desgin.pattern.creational.singleton;

import java.io.Serializable;

/**
 * Create by lastwhisper on 2019/1/26
 */
public class HungrySingleton implements Serializable,Cloneable {
    private static HungrySingleton hungrySingleton;

    static {
        hungrySingleton = new HungrySingleton();
    }

    private HungrySingleton() {
        if (hungrySingleton != null) {
            throw new RuntimeException("单例构造器禁止反射调用");
        }
    }

    public static HungrySingleton getInstance() {
        return hungrySingleton;
    }

    private Object readResolve() {
        return hungrySingleton;
    }

//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }

    @Override
    protected Object clone(){
        return getInstance();
    }
}
