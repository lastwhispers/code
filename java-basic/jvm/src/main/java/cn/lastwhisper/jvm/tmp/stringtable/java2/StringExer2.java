package cn.lastwhisper.jvm.tmp.stringtable.java2;

import org.junit.Test;

/**
 *
 * @author shkstart  shkstart@126.com
 * @create 2020  20:26
 */
public class StringExer2 {
    public static void main(String[] args) {
        String s1 = new String("ab");//执行完以后，会在字符串常量池中会生成"ab"
//        String s1 = new String("a") + new String("b");////执行完以后，不会在字符串常量池中会生成"ab"
        s1.intern();
        String s2 = "ab";
        System.out.println(s1 == s2);// true
    }

    @Test
    public void test1() {
        String s1 = new String("ab");//执行完以后，会在字符串常量池中会生成"ab"
        s1.intern();
        String s2 = "ab";
        System.out.println(s1 == s2);// true
    }

    @Test
    public void test2() {
        String s1 = new String("a") + new String("b");////执行完以后，不会在字符串常量池中会生成"ab"
        s1.intern();
        String s2 = "ab";
        System.out.println(s1 == s2);// jdk6:false jdk8:true
    }

}
