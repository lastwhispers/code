package com.imooc.flink.domain;

public class Product {

    public String category;
    public String name;

    @Override
    public String toString() {
        return "Product{" +
                "category='" + category + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
