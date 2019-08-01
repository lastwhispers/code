package cn.lastwhisper.lifecycle.bean;

/**
 * @author lastwhisper
 */
public class Car {

    public Car() {
        System.out.println("car constructor...");
    }
    // 初始化资源线程池、连接池等
    public void init() {
        System.out.println("car init...");
    }
    // 释放资源
    public void destroy() {
        System.out.println("car destroy...");
    }
}
