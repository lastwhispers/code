package com.desgin.pattern.structural.proxy.my.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * @author lastwhisper
 */
public class CalculatorDynamicProxy implements InvocationHandler {
    // 目标类
    private Object target;

    // 代理类通过构造器接收目标对象（被代理类）
    public CalculatorDynamicProxy(Object target) {
        this.target = target;
    }

    /** 获取目标对象 */
    public Object getProxy() {
        Class clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    /**
     * proxy:代理类代理的真实代理对象com.sun.proxy.$Proxy0
     * method:我们所要调用某个对象真实的方法的Method对象
     * args:指代代理对象方法传递的参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object argObject = args[0];
        beforeMethod();
        Object object = method.invoke(target, args);
        afterMethod();
        return object;
    }

    public void beforeMethod() {
        System.out.println("proxy代理，日志开始记录");
    }

    public void afterMethod() {
        System.out.println("proxy代理，日志结束，已记录到数据库中");
    }
}
