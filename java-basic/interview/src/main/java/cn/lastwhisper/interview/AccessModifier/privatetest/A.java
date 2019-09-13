package cn.lastwhisper.interview.AccessModifier.privatetest;

/**
 * @author lastwhisper
 */
public class A {
    // private修饰的变量——》私有变量
    private int a;
    // private修饰的方法——》私有方法
    private int sum(int m, int n) {
        return m + n;
    }
    public int getA() {
        return a;
    }
    public static void main(String[] args) {
        A aObject = new A();
        System.out.println(aObject.a);
    }

}


