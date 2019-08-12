package com.desgin.pattern.behavioral.visitor;

/**
 * Create by lastwhisper on 2019/2/11
 */
public interface IVisitor {
    void visit(FreeCourse freeCourse);

    void visit(CodingCourse codingCourse);
}
