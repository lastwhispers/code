package com.desgin.principle.dependencyInversion.v2;

/**
 * @author lastwhisper
 */
public class Client {
    public static void main(String[] args){
        // 此时动态修改轮胎的大小不需要修改Car、Framework、Bottom、Tire的内部代码

        // 这段初始化代码就是 控制反转容器
        Tire tire = new Tire(30);
        Bottom bottom = new Bottom(tire);
        Framework framework = new Framework(bottom);
        Car car = new Car(framework);
        car.run();
    }
}
