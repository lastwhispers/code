package cn.lastwhisper.jvm.classloading.initiative;

/**
 * 主动引用触发初始化、演示三
 * @author lastwhisper
 */
public class Initialization3 {
    // 当初始化一个类的时候，如果发现其父类还没有进行过初始化，
    // 则需要先触发其父类的初始化
    public static void main(String[] args) {
        // 在Initialization1的new指令和Initialization2的反射创建对象都有体现
        SubClass subClass = new SubClass();
        //初始化顺序
        //SuperClass Static code init!  首先初始化父类静态代码块
        //SubClass Static code init!    其次初始化自己的静态代码块
        //SuperClass constructor init!  其次初始化父类的构造器
        //SubClass constructor init!    其次初始化自己的构造器
    }
}
