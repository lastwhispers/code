package com.desgin.pattern.behavioral.templatemethod.my;

//炒蒜蓉菜心的类
public class ConcreteClass_CaiXin extends Abstract {
    @Override
    public void pourVegetable() {
        System.out.println("下锅的蔬菜是菜心");
    }

    @Override
    public void pourSauce() {
        System.out.println("下锅的酱料是蒜蓉");
    }
}