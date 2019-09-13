package com.desgin.principle.dependencyInversion.v1;

/**
 * @author lastwhisper
 */
public class Client {
    public static void main(String[] args){
        // 改动一下轮胎（Tire）类，把它的尺寸变成动态，比较麻烦
        // 需要修改源代码
        Car car = new Car();
        car.run();
    }
}
