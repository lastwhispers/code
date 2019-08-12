package com.desgin.pattern.creational.builder;

/**
 * Create by lastwhisper on 2019/1/24
 */
public class Course {
    private String courseName;
    private String coursePPT;
    private String courseVideo;
    private String courseArticle;
    private String courseQA;

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", coursePPT='" + coursePPT + '\'' +
                ", courseVideo='" + courseVideo + '\'' +
                ", courseArticle='" + courseArticle + '\'' +
                ", courseQA='" + courseQA + '\'' +
                '}';
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCoursePPT() {
        return coursePPT;
    }

    public void setCoursePPT(String coursePPT) {
        this.coursePPT = coursePPT;
    }

    public String getCourseVideo() {
        return courseVideo;
    }

    public void setCourseVideo(String courseVideo) {
        this.courseVideo = courseVideo;
    }

    public String getCourseArticle() {
        return courseArticle;
    }

    public void setCourseArticle(String courseArticle) {
        this.courseArticle = courseArticle;
    }

    public String getCourseQA() {
        return courseQA;
    }

    public void setCourseQA(String courseQA) {
        this.courseQA = courseQA;
    }
}
