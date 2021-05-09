package cn.cunchang;

import java.util.Random;

/**
 * @author cunchang
 * @date 2021/5/9 9:57 上午
 */
public class Tank implements Moveable {

    @Override
    public void move() {
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Tank Moving...");
    }

    /**
     * 直接修改源码
     */
//    @Override
//    public void move() {
//        //计算方法运行了多长时间
//        long start = System.currentTimeMillis();
//
//        try {
//            Thread.sleep(new Random().nextInt(1000));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Tank Moving...");
//        long end = System.currentTimeMillis();
//        System.out.println("耗时："+(end-start));
//    }

}