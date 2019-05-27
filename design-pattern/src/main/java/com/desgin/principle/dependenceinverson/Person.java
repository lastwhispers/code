package com.desgin.principle.dependenceinverson;

/**
 * Create by lastwhisper on 2019/1/22
 */
public class Person {
//    public void studyJavaCourse(){
//        System.out.println("person在学习java课程");
//    }
//
//    public void studyFECourse(){
//        System.out.println("person在学习前端课程");
//    }
//
//    public void studyPythonCourse(){
//        System.out.println("person在学习Python课程");
//    }

    private ICourse iCourse;


    public void setiCourse(ICourse iCourse) {
        this.iCourse = iCourse;
    }

    // v2
//    public void studyImoocCourse(ICourse iCourse){
//        iCourse.studyCourse();
//    }

    public void studyImoocCourse(){
        iCourse.studyCourse();
    }


}
