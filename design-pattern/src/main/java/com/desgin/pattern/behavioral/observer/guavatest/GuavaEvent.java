package com.desgin.pattern.behavioral.observer.guavatest;

import com.google.common.eventbus.Subscribe;

/**
 * Create by lastwhisper on 2019/2/11
 */
public class GuavaEvent {
    @Subscribe
    public void subscribe(String str) {
        System.out.println("执行subscribe方法，传入：" + str);
    }
}
