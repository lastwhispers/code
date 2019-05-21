package com.desgin.pattern.structural.proxy;

public interface IOrderService {
    /** 保存订单，参数为订单对象，返回值为生效行数 */
    int saveOrder(Order order);
}
