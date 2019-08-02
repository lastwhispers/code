package cn.lastwhisper.jvm.classloading.initiative;

/**
 * 主动引用触发初始化、演示四
 * @author lastwhisper
 */
public class Initialization4 {

    static {
        System.out.println("Initialization4 Static code init!");
    }

    public Initialization4() {
        System.out.println("Initialization4 constructor init!");
    }

    public static void main(String[] args) {
        // 当虚拟机启动时，用户需要指定一个要执行的主类(包含main()方法的那个类)，虚拟机会先初始化这个主类。
    }
}
