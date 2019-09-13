package com.desgin.principle.dependencyInversion.v2;

/**
 * @author lastwhisper
 */
public class Car {
    private Framework framework;

    public Car(Framework framework) {
        this.framework = framework;
    }

    public void run() {
        System.out.println("汽车跑起来了");
    }
}
