package com.desgin.pattern.structural.flyweight.application;

import org.junit.Test;

import java.util.Properties;

/**
 * 修改IntegerCache
 * -Djava.lang.Integer.IntegerCache.high=129
 * -XX:AutoBoxCacheMax=129
 * @author lastwhisper
 */
public class IntegerDemo {
    // 测试IntegerCache
    @Test
    public void test1(){
        Integer a1 = 127;//Integer.valueOf(127);
        Integer a2 = 127;
        System.out.println(a1 == a2);

        Integer a3 = 129;//Integer.valueOf(129);
        Integer a4 = 129;
        System.out.println(a3 == a4);

        Integer a5 = new Integer(127);
        Integer a6 = new Integer(127);
        System.out.println(a5 == a6);
    }
    //
    @Test
    public void test2(){
        int one = 1;
        int two = one + one;
        System.out.println(two == 2);
        System.out.println(Integer.valueOf(two) == 2);
        System.out.println(new Integer(2) == 2);
    }
}
