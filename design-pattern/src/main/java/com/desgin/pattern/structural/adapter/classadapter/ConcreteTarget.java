package com.desgin.pattern.structural.adapter.classadapter;

/**
 * 具体的实现类
 */
public class ConcreteTarget implements Target {
    @Override
    public void request() {
        System.out.println("ConcreteTarget目标方法");
    }
}
