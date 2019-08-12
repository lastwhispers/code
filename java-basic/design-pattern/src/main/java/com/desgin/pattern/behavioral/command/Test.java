package com.desgin.pattern.behavioral.command;

public class Test {
    public static void main(String[] args) {
        //通过请求者（Staff）调用命令对象（command），命令对象中调用了命令具体执行者（CourseVideo）
        CourseVideo courseVideo = new CourseVideo("Java设计模式");
        OpenCourseVideoCommand openCourseVideoCommand = new OpenCourseVideoCommand(courseVideo);
        CloseCourseVideoCommand closeCourseVideoCommand = new CloseCourseVideoCommand(courseVideo);

        Staff staff = new Staff();
        staff.addCommand(openCourseVideoCommand);
        staff.addCommand(closeCourseVideoCommand);
        staff.executeCommands();
    }
}
