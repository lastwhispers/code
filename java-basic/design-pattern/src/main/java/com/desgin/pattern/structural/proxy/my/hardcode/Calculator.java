package com.desgin.pattern.structural.proxy.my.hardcode;
// 硬编码
public class Calculator {

    //加
    public int add(int a, int b) {
        System.out.println("add日志开始记录");
        int result = a + b;
        System.out.println("add日志结束，已记录到数据库中");
        return result;
    }

    //减
    public int subtract(int a, int b) {
        System.out.println("subtract日志开始记录");
        int result = a - b;
        System.out.println("subtract日志结束，已记录到数据库中");
        return result;
    }

    //乘法、除法...
}