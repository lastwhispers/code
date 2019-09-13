package cn.lastwhisper.interview.Initiative;

/**
 * 父类
 * @author lastwhisper
 */
public class SuperClass {

    static {
        System.out.println("父类的静态代码块");
    }

    {
        System.out.println("父类的代码块");
    }

    public SuperClass() {
        System.out.println("父类的构造函数");
    }
}

