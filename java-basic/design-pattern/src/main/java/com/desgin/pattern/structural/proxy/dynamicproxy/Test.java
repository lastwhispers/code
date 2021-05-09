package com.desgin.pattern.structural.proxy.dynamicproxy;

import com.desgin.pattern.structural.proxy.biz.IOrderService;
import com.desgin.pattern.structural.proxy.biz.Order;
import com.desgin.pattern.structural.proxy.biz.OrderServiceImpl;

public class Test {
    public static void main(String[]args){
        Order order = new Order();
        order.setUserId(2);
        /** 这里没有采用spring自动注入的方式，而是采用了直接new的方式 */
        IOrderService orderServiceProxy = (IOrderService) new OrderServiceDynamicProxy(new OrderServiceImpl()).getProxy();
        orderServiceProxy.saveOrder(order);
    }
}
