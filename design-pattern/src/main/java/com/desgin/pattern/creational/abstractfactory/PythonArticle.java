package com.desgin.pattern.creational.abstractfactory;

/**
 * Create by lastwhisper on 2019/1/24
 */
public class PythonArticle extends Article {
    @Override
    public void produce() {
        System.out.println("编写Python课程手记");
    }
}
