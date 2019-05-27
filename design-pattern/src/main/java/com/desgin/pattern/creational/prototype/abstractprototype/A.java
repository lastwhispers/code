package com.desgin.pattern.creational.prototype.abstractprototype;

/**
 * Create by lastwhisper on 2019/1/27
 */
public abstract class A implements Cloneable{
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
