package com.desgin.pattern.behavioral.visitor;

/**
 * Create by lastwhisper on 2019/2/11
 */
public abstract class Course {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void accept(IVisitor visitor);
}
