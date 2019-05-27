package com.desgin.pattern.behavioral.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者类
 * @author lastwhisper
 */
public class Teacher implements Observer {
    private String teacherName;

    public Teacher(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public void update(Observable o, Object arg) {
        Course course = (Course) o;
        Question question = (Question) arg;
        System.out.println(teacherName+"老师的"+course.getCourseName()+"课程接收到一个"+question.getUserName()+"同学提交的问答："+question.getQuestionContent());
    }
}
