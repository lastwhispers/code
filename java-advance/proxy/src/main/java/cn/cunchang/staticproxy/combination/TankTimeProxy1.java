package cn.cunchang.staticproxy.combination;

import cn.cunchang.Moveable;

/**
 * @author cunchang
 * @date 2021/5/9 9:57 上午
 */
public class TankTimeProxy1 implements Moveable {

    Moveable m;
    public TankTimeProxy1(Moveable m) {
        super();
        this.m = m;
    }
    @Override
    public void move() {
        //计算方法运行了多长时间
        long start = System.currentTimeMillis();
        m.move();
        long end = System.currentTimeMillis();
        System.out.println("耗时："+(end-start));
    }

//    @Override
//    public void stop() {
//        //计算方法运行了多长时间
//        long start = System.currentTimeMillis();
//        System.out.println("start:"+start);
//        m.stop();
//        long end = System.currentTimeMillis();
//        System.out.println("耗时："+(end-start));
//    }
}