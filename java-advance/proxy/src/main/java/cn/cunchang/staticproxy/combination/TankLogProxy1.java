package cn.cunchang.staticproxy.combination;

import cn.cunchang.Moveable;

/**
 * @author cunchang
 * @date 2021/5/9 9:57 上午
 */
public class TankLogProxy1 implements Moveable {

    Moveable m;
    public TankLogProxy1(Moveable m) {
        super();
        this.m = m;
    }

    @Override
    public void move() {
        System.out.println("Tank start....");
        m.move();
        System.out.println("Tank end....");
    }

}