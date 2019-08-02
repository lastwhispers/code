package cn.lastwhisper.jvm.classloading.passive;

/**
 * 被动使用类字段不触发初始化、演示三
 * @author lastwhisper
 */
public class NotInitialization3 {
    public static void main(String[] args) {
        // 常量在编译阶段会进行常量优化，将常量存入**调用类**的常量池中，
        // 本质上并没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化。
        System.out.println(ConstClass.HELLOWORLD);
        // hello world
    }
}
