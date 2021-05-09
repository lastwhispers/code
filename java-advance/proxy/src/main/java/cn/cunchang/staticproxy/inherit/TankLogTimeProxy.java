package cn.cunchang.staticproxy.inherit;

public class TankLogTimeProxy extends TankTimeProxy {

    @Override
    public void move() {
        //记录日志
        System.out.println("Tank start....");
        super.move();
        System.out.println("Tank end....");
    }


}