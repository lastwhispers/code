package cn.lastwhisper.jvm.classloading.passive;

import cn.lastwhisper.jvm.classloading.initiative.SubClass;

/**
 * 被动使用类字段不触发初始化、演示一
 * @author lastwhisper
 */
public class NotInitialization1 {
    // 子类引用父类的静态字段，只会触发子类的加载、父类的初始化，不会导致子类初始化
    // 是否要触发子类的加载和验证，在虚拟机规范中并未明确规定，这点取决于虚拟机的具体实现
    // 对于Sun HotSpot虚拟机，可通过-XX:+TraceClassLoading参数观察到此操作会导致子类的加载
    public static void main(String[] args) {
        System.out.println(SubClass.value);
        //[Loaded cn.lastwhisper.jvm.classloading.passive.SubClass from ...]
    }
}
