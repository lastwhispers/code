package com.desgin.pattern.behavioral.observer.my;

/***
 * 抽象观察者
 * 定义了一个update()方法，当被观察者调用notifyObservers()方法时，观察者的update()方法会被回调。
 * @author lastwhisper
 *
 */
public interface Observer {
    /**
     * 观察者接收通知
     * @param message
     */
    void update(String message);
}