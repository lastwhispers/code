package com.desgin.principle.singleresponsibility;

/**
 * Create by lastwhisper on 2019/1/22
 */
public class Bird {
    public void mainMoveMode(String birdName){
        if("鸵鸟".equals(birdName)){
            System.out.println(birdName + "用脚走");
        }else {
            System.out.println(birdName + "用翅膀飞");
        }
    }
}
