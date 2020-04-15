package com.desgin.advance.decorator_proxy.decorator;

import com.desgin.advance.decorator_proxy.Boxer;

/**
 * 移速鞋
 * @author lastwhisper
 * @date 2020/4/12
 */
public class FastShoesDecorator implements Boxer {
    private Boxer boxer;

    public FastShoesDecorator(Boxer boxer) {
        this.boxer = boxer;
    }

    public void moveFast() {
        System.out.println("穿上非常快的鞋：移速+5");
    }

    @Override
    public void fight() {
        boxer.fight();
    }

}
