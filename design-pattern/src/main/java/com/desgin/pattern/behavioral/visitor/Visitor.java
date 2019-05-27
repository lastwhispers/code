package com.desgin.pattern.behavioral.visitor;

/**
 * Create by lastwhisper on 2019/2/11
 */
public class Visitor implements IVisitor {
    /**
     * 访问免费课程，打印所有免费课程名称
     *
     * @param freeCourse
     */
    @Override
    public void visit(FreeCourse freeCourse) {
        System.out.println("免费课程：" + freeCourse.getName());
    }

    /**
     * 访问实战课程，打印所有实战课程名称以及价格
     *
     * @param codingCourse
     */
    @Override
    public void visit(CodingCourse codingCourse) {
        System.out.println("实战课程：" + codingCourse.getName() + " 价格：" + codingCourse.getPrice()+ "元");
    }
}
