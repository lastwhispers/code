package com.desgin.principle.openclose;

/**
 * Create by lastwhisper on 2019/1/22
 */
public class JavaCourse implements ICourse {

    private  Integer Id;
    private String name;
    private Double price;

    public JavaCourse(Integer id, String name, Double price) {
        Id = id;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

//    public Double getDiscountPrice() {
//        return this.price*0.8;
//    }
}
