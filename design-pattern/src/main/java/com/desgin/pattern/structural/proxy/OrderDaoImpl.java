package com.desgin.pattern.structural.proxy;

public class OrderDaoImpl implements IOrderDao {
    @Override
    public int insert(Order order) {
        System.out.println("Dao层添加order成功");
        return 1;
    }
}
