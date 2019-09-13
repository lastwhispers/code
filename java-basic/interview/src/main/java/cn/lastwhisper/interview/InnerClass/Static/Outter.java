package cn.lastwhisper.interview.InnerClass.Static;

public class Outter {
    private static int b = 2;
    private int a = 1;

    protected static class Inner {
        public Inner() {
            //System.out.println(a);//成员变量 会报错“Non-static field 'a' cannot be referenced from a static context”
            System.out.println(b);//static变量
            //print();//成员函数 会报错“Non-static field 'a' cannot be referenced from a static context”
            staticPrint();//static函数
        }
    }

    private void print() {
    }

    private static void staticPrint() {
    }
}