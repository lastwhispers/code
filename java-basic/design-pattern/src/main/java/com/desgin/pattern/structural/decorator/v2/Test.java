package com.desgin.pattern.structural.decorator.v2;

public class Test {
    public static void main(String[]args){
        ABattercake aBattercake;
        aBattercake = new Battercake();
        aBattercake = new EggDecorator(aBattercake);
        aBattercake = new EggDecorator(aBattercake);
        aBattercake = new SausageDecorator(aBattercake);
        System.out.println(aBattercake.getDesc()+"价格为:"+aBattercake.cost());
    }
}
