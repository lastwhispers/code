package cn.lastwhisper.javabasic.AccessModifier.protectedtest;

import cn.lastwhisper.javabasic.AccessModifier.A2;

/**
 * @author lastwhisper
 */
public class B {
    public static void main(String[] args) {
        //A2 aObject = new A2();
        A aObject = new A();
        // protected访问修饰，同包可以访问，不同包不可以访问
        aObject.a = 10;
    }
}
