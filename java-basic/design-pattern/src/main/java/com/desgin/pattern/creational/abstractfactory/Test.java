package com.desgin.pattern.creational.abstractfactory;


/**
 * Create by lastwhisper on 2019/1/24
 */
public class Test {
    public static void main(String[] args){
       CourseFactory courseFactory = new JavaCourseFactory();
        Video video = courseFactory.getVideo();
        Article article = courseFactory.getArticle();
        article.produce();
        video.produce();
    }
}
