package cn.lastwhisper.javabasic.Initiative;

/**
 * 子类
 * @author lastwhisper
 */
public class SubClass extends SuperClass {

    static {
        System.out.println("子类的静态代码块");
    }

    {
        System.out.println("子类的代码块");
    }

    public SubClass() {
        System.out.println("子类的构造函数");
    }

    public static void main(String[] args) {
        new SubClass();
    }
}
