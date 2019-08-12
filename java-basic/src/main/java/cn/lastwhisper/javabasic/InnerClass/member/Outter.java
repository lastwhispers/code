package cn.lastwhisper.javabasic.InnerClass.member;

public class Outter {
    private static int b = 2;
    private int a = 1;

    protected class Inner {
        public Inner() {
            System.out.println(a);//成员变量
            System.out.println(b);//static变量
            print();//成员函数
            staticPrint();//static函数
        }
    }
    private void print() {
    }

    private static void staticPrint() {
    }
}