package com.desgin.pattern.structural.facade;

public class ShippingService {
    public String shipGift(PointsGift pointsGift) {
        /** 物流系统对接逻辑 */
        System.out.println(pointsGift.getName() + "进入物流系统");
        String shippingOrderNo = "666";
        return shippingOrderNo;
    }
}