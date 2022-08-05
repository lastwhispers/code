package cn.lastwhisper.jvm.tmp.stringtable.java2;

import org.junit.Test;

/**
 * @author shkstart  shkstart@126.com
 * @create 2020  20:17
 */
public class StringExer1 {

    @Test
    public void test1() {
        String s = new String("a") + new String("b");//new String("ab")
        //jdk6、8，在上一行代码执行完以后，字符串常量池中并没有"ab"

        //jdk6中：在串池中创建一个字符串"ab"
        //jdk8中：串池中没有创建字符串"ab",而是创建一个引用，指向new String("ab")，将此引用返回
        String s2 = s.intern();

        System.out.println(s2 == "ab");//jdk6:true  jdk8:true
        System.out.println(s == "ab");//jdk6:false  jdk8:true

    }

    @Test
    public void test2() {
        String xxx = "ab";
        // 此时常量池已经有"ab"

        String s = new String("a") + new String("b");//new String("ab")

        String s2 = s.intern();

        System.out.println(s2 == "ab");//jdk6:true  jdk8:true
        System.out.println(s == "ab");//jdk6:false  jdk8:false
    }

}
