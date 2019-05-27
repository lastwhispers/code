package com.desgin.pattern.structural.proxy.dynamicproxy;

import com.desgin.pattern.structural.proxy.Order;
import com.desgin.pattern.structural.proxy.db.DataSourceContextHolder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Create by lastwhisper on 2019/2/10
 */
public class OrderServiceDynamicProxy implements InvocationHandler {

    /** 这是目标类 */
    private Object target;

    /** 通过构造器把目标类注入进来 */
    public OrderServiceDynamicProxy(Object target) {
        this.target = target;
    }

    /** 进行绑定目标对象 */
    public Object bind() {
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
        beforeMethod(argObject);
        Object object = method.invoke(target, args);
        afterMethod();
        return object;
    }

    public void beforeMethod(Object obj) {
        int userId = 0;
        System.out.println("动态代理before code");
        if (obj instanceof Order) {
            Order order = (Order) obj;
            userId = order.getUserId();
        }
        /** 这里就是实现一个分库的功能,对userId取模2，这里就只会得到0或者是1 */
        int dbRouter = userId % 2;
        System.out.println("动态代理分配到【db"+dbRouter+"】处理数据");
        //todo 设置dataSource;
        DataSourceContextHolder.setDBType(String.valueOf(dbRouter));
    }
    public void afterMethod() {
        System.out.println("动态代理after code");
    }
}
