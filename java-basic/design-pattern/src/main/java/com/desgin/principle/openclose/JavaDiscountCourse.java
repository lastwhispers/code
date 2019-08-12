package com.desgin.principle.openclose;

/**
 * Create by lastwhisper on 2019/1/22
 */
public class JavaDiscountCourse extends JavaCourse {
    public JavaDiscountCourse(Integer id, String name, Double price) {
        super(id, name, price);
    }

    public Double getOriginPrice(){
        return super.getPrice();
    }

    @Override
    public Double getPrice() {
        //如果当前价格大于300打8折；大于400打7折
        return super.getPrice()*0.8;
    }
}
