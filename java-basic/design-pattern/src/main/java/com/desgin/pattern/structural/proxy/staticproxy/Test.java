package com.desgin.pattern.structural.proxy.staticproxy;

import com.desgin.pattern.structural.proxy.Order;

public class Test {
    public static void main(String[]args){
        Order order = new Order();
        order.setUserId(0);
        /** 这里没有采用spring自动注入的方式，而是采用了直接new的方式 */
        OrderServiceStaticProxy orderServiceStaticProxy = new OrderServiceStaticProxy();
        orderServiceStaticProxy.saveOrder(order);
    }
}
