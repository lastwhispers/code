package com.desgin.pattern.behavioral.observer.my;

/***
 * 抽象被观察者
 * 声明了添加、删除、通知观察者方法
 * @author lastwhisper
 */
public interface Observerable {
    /**
     * 注册观察者
     * @param o
     */
    void registerObserver(Observer o);

    /**
     * 移除观察者
     * @param o
     */
    void removeObserver(Observer o);

    /**
     * 通知观察者
     */
    void notifyObserver();

}