package com.desgin.pattern.creational.builder.V2;

/**
 * Create by lastwhisper on 2019/1/25
 */
public class Test {
    public static void main(String[] args){
        Course course = new Course.CourseBuilder()
                .buildCourseName("Java设计模式")
                .buildCoursePPT("Java设计模式PPT")
                //.buildCourseVideo("Java设计模式视频")
                //.buildCourseArticle("Java设计模式手记")
                .buildCourseQA("Java设计模式问答").build();
        System.out.println(course);
    }
}
