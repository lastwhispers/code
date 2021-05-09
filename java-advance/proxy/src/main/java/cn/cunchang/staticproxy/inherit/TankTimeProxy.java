package cn.cunchang.staticproxy.inherit;

import cn.cunchang.Tank;

/**
 * @author cunchang
 * @date 2021/5/9 9:57 上午
 */
public class TankTimeProxy extends Tank {

    @Override
    public void move() {
        //计算方法运行了多长时间
        long start = System.currentTimeMillis();
        super.move();
        long end = System.currentTimeMillis();
        System.out.println("耗时："+(end-start));
    }

}