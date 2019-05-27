package com.desgin.principle.openclose;

/**
 * Create by lastwhisper on 2019/1/22
 */
public class Test {
    public static void main(String[] args) {
//        ICourse javaCourse = new JavaCourse(96,"javaee",348d);
//        System.out.println("课程ID:"+javaCourse.getId()+"  课程名称:"+javaCourse.getName()+"  课程价格:"+javaCourse.getPrice());

        ICourse iCourse = new JavaDiscountCourse(96,"javaee",348d);
        JavaDiscountCourse javaCourse = (JavaDiscountCourse) iCourse;
        System.out.println("课程ID:"+javaCourse.getId()+"  课程名称:"+javaCourse.getName()+"  课程原价价格:"+javaCourse.getOriginPrice()+"  课程打折价格:"+javaCourse.getPrice());

    }
}

