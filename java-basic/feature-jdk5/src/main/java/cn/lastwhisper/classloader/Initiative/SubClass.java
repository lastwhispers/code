package cn.lastwhisper.classloader.Initiative;

/**
 * 子类
 * @date 2019/8/12
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
