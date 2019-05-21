package com.desgin.pattern.behavioral.strategy;

public class FanXianPromotionStrategy implements PromotionStrategy {

    @Override
    public void doPromotion() {
        System.out.println("反现促销，返回的金额存放到网站用户的余额中");
    }
}
