package com.roy.rocketmq.domain;

import java.math.BigDecimal;

/**
 * @author ：楼兰
 * @date ：Created in 2020/11/28
 * @description:
 **/

public class OrderPaidEvent {
    private String orderId;

    private BigDecimal paidMoney;

    public OrderPaidEvent() {
    }

    public OrderPaidEvent(String orderId, BigDecimal paidMoney) {
        this.orderId = orderId;
        this.paidMoney = paidMoney;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(BigDecimal paidMoney) {
        this.paidMoney = paidMoney;
    }
}
