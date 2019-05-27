package com.desgin.pattern.behavioral.state;

/**
 * Create by lastwhisper on 2019/2/11
 */
public class StopState extends CourseVideoState  {
    @Override
    public void play() {
        super.courseVideoContext.setCourseVideoState(CourseVideoContext.PLAY_STATE);
    }

    @Override
    public void speed() {
        System.out.println("ERROR 停止状态不能快进！！");
    }

    @Override
    public void pause() {
        System.out.println("ERROR 停止状态不能暂停！！");
    }

    @Override
    public void stop() {
        System.out.println("停止播放课程视频状态");
    }
}
