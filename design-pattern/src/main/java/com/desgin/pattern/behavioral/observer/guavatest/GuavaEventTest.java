package com.desgin.pattern.behavioral.observer.guavatest;

import com.google.common.eventbus.EventBus;

/**
 * Create by lastwhisper on 2019/2/11
 */
public class GuavaEventTest {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        GuavaEvent guavaEvent = new GuavaEvent();
        eventBus.register(guavaEvent);
        eventBus.post("post的内容");
    }

}
