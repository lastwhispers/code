package com.desgin.pattern.structural.decorator.v1;

public class BattercakeWithEggSausage extends BattercakeWithEgg {
    @Override
    public String getDesc() {
        return super.getDesc() + "一个香肠";
    }

    @Override
    public int cost() {
        return super.cost() + 2;
    }
}
