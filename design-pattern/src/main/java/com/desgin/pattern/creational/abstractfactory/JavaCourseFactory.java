package com.desgin.pattern.creational.abstractfactory;

/**
 * Create by lastwhisper on 2019/1/24
 */
public class JavaCourseFactory implements CourseFactory {
    @Override
    public Video getVideo() {
        return new JavaVideo();
    }
    @Override
    public Article getArticle() {
        return new JavaArticle();
    }
}
