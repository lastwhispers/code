package cn.lastwhisper.concurrent.example.future;

//公共data数据接口
public abstract class Data {
    //方法作用 返回线程执行结果
    public abstract String getRequest();
}