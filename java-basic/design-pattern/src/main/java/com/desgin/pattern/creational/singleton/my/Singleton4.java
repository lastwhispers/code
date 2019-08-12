package com.desgin.pattern.creational.singleton.my;

/**
 * 懒汉式：静态内部类
 * @author lastwhisper
 */
public class Singleton4 {
    private Singleton4() {
    }

    private static class StaticInnerClass {
        private static Singleton4 instance = new Singleton4();
    }

    public Singleton4 getInstance() {
        return StaticInnerClass.instance;
    }


}
