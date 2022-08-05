package cn.lastwhisper.jvm.tmp.stringtable.java;

import org.junit.Test;

/**
 * String的基本使用:体现String的不可变性
 *
 * @author shkstart  shkstart@126.com
 * @create 2020  23:42
 */
public class StringTest1 {

    /**
     * s1 == s2为true，说明两个指向堆中同一处引用
     * 修改s2的值，s2的地址发生变化，说明s2指向堆中新的地址
     *
     */
    @Test
    public void test1() {
        String s1 = "abc";//字面量定义的方式，"abc"存储在字符串常量池中
        String s2 = "abc";
        System.out.println(s1 == s2);//判断地址：true

        s1 = "hello";

        System.out.println(s1 == s2);//判断地址：false

        System.out.println(s1);//
        System.out.println(s2);//abc

    }

    /**
     * String所有操作都会创建新的字符串
     */
    @Test
    public void test2() {
        String s1 = "abc";
        String s2 = "abc";
        s2 += "def";
        System.out.println(s2);//abcdef
        System.out.println(s1);//abc
    }

    /**
     * String所有操作都会创建新的字符串
     */
    @Test
    public void test3() {
        String s1 = "abc";
        String s2 = s1.replace('a', 'm');
        System.out.println(s1);//abc
        System.out.println(s2);//mbc
    }
}
