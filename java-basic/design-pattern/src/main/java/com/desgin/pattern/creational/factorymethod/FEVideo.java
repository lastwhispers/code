package com.desgin.pattern.creational.factorymethod;

/**
 * Create by lastwhisper on 2019/1/24
 */
public class FEVideo extends Video {
    @Override
    public void produce() {
        System.out.println("录制前端课程视频");
    }
}
