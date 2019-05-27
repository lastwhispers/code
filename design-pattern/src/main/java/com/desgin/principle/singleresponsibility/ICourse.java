package com.desgin.principle.singleresponsibility;

/**
 * Create by lastwhisper on 2019/1/22
 */
public interface ICourse {
    String getCourseName();

    byte[] getCourseVideo();

    void studyCourse();

    void refundCourse();
}
