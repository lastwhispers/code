package com.desgin.pattern.creational.prototype.my;

import java.util.Date;

public class Resume implements Cloneable {
    private String name;
    private Date birthday;
    private String sex;
    private String school;
    private String timeArea;
    private String company;

    public Resume(String name, Date birthday, String sex, String school, String timeArea, String company) {
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
        this.school = school;
        this.timeArea = timeArea;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTimeArea() {
        return timeArea;
    }

    public void setTimeArea(String timeArea) {
        this.timeArea = timeArea;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 克隆该实例
     */
    @Override
    public Object clone() {
        Resume resume = null;
        try {
            resume = (Resume) super.clone();
            resume.birthday = (Date) resume.birthday.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return resume;
    }

    public void display() {
        System.out.println("姓名：" + name);
        System.out.println("生日:" + birthday + ",性别:" + sex + ",毕业学校：" + school);
        System.out.println("工作年限:" + timeArea + ",公司:" + company);
    }

}