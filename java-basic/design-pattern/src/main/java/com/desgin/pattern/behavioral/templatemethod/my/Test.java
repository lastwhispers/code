package com.desgin.pattern.behavioral.templatemethod.my;

public class Test {
    public static void main(String[] args) {
        //炒 - 手撕包菜
        ConcreteClass_BaoCai BaoCai = new ConcreteClass_BaoCai();
        BaoCai.cookProcess();
        //炒 - 蒜蓉菜心
        ConcreteClass_CaiXin CaiXin = new ConcreteClass_CaiXin();
        CaiXin.cookProcess();
    }

}