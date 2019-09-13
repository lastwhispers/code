package com.desgin.principle.dependencyInversion.v1;

/**
 * @author lastwhisper
 */
public class Car {
    private Framework framework;

    public Car() {
        this.framework = new Framework();
    }

    public void run() {
        System.out.println("汽车跑起来了");
    }
}
