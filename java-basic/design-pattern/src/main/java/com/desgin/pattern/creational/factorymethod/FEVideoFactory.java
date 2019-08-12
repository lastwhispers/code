package com.desgin.pattern.creational.factorymethod;

/**
 * Create by lastwhisper on 2019/1/24
 */
public class FEVideoFactory extends  VideoFactory {
    public Video getVideo() {
        return new FEVideo();
    }
}
