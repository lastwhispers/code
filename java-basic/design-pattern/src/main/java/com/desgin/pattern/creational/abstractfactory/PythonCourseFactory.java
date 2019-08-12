package com.desgin.pattern.creational.abstractfactory;

/**
 * Create by lastwhisper on 2019/1/24
 */
public class PythonCourseFactory implements CourseFactory {
    @Override
    public Video getVideo() {
        return new PythonVideo();
    }

    @Override
    public Article getArticle() {
        return new PythonArticle();
    }
}
