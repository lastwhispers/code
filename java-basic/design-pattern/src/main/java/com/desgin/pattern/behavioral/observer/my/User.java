package com.desgin.pattern.behavioral.observer.my;

/**
 * 具体观察者
 * 实现了update方法
 *
 * @author lastwhisper
 */
public class User implements Observer {

    private String name;
    private String message;

    public User(String name) {
        this.name = name;
    }

    public void read() {
        System.out.println(name + " 收到推送消息： " + message);
    }

    @Override
    public void update(String message) {
        this.message = message;
        read();
    }
}
