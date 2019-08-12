package com.desgin.pattern.creational.singleton.my;

/**
 * 饿汉式
 * 使用静态变量
 * @author lastwhisper
 */
public class Singleton1 {
    public static Singleton1 instance = new Singleton1();

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        return instance;
    }

}
