package com.desgin.pattern.creational.prototype.my;

import java.util.Date;

/**
 * Create by lastwhisper on 2019/2/7
 */
public class Test {
    public static void main(String[] args) {
        //原型A对象
        Resume a = new Resume("小李子",new Date(), "男", "XX大学","2012.09.05", "XX科技有限公司");
        a.display();
        System.out.println("*************克隆简历**************");
        //克隆B对象
        Resume b = (Resume) a.clone();
        b.display();
        System.out.println("***************比较***************");
        /*
         * 测试A==B?
         * 对任何的对象x，都有x.clone() !=x，即克隆对象与原对象不是同一个对象
         */
        System.out.print("比较：A==B?");
        System.out.println( a == b);

        b.getBirthday().setTime(666666666666L);
        System.out.println("修改B简历的Birthday为："+b.getBirthday());
        System.out.println("查看A简历的Birthday为："+a.getBirthday());

        /*
         * 比较Date对象
         */
        System.out.print("比较：A.Date==B.Date?");
        System.out.println(a.getBirthday() == b.getBirthday());
    }
}
