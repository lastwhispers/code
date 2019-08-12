package com.desgin.principle.demeter;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by lastwhisper on 2019/1/22
 */
public class TeamLeader {
    public void checkNumberOfCourse(){
        List<Course> courseList = new ArrayList<Course>();
        for(int i =0;i<20 ;i++){
            courseList.add(new Course());
        }
        System.out.println("在线课程数量："+courseList.size());
    }
}
