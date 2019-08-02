package cn.lastwhisper.jvm.classloading.passive;

public interface Interface {
    //一个接口在初始化时，并不要求初始化其全部接口，
    // 只会初始化使用到的接口时（如引用接口中定义的常量），才会初始化.
}
