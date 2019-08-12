package com.desgin.pattern.creational.prototype.clone;

import com.desgin.pattern.creational.singleton.HungrySingleton;

/**
 * Create by lastwhisper on 2019/1/27
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
//        Date birthday = new Date(0L);
//        Pig pig1 = new Pig("佩奇", birthday);
//        Pig pig2 = (Pig) pig1.clone();
//        System.out.println(pig1);
//        System.out.println(pig2);
//        pig1.getBirthday().setTime(666666666666L);
//        System.out.println(pig1);
//        System.out.println(pig2);
        HungrySingleton instance = HungrySingleton.getInstance();

    }
}
