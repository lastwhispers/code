package com.desgin.pattern.behavioral.iterator;

public interface CourseAggregate {

    void addCourse(Course course);
    void removeCourse(Course course);
	/**获取迭代器 */
    CourseIterator getCourseIterator();
}
