package com.desgin.pattern.creational.simplefactory;

/**
 * Create by lastwhisper on 2019/1/23
 */
public class Test {
    public static void main(String[] args) {
        Video video = VideoFactory.getVideo(JavaVideo.class);
        video.produce();

    }
}
