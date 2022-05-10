package cn.lastwhisper.concurrent.basic.synchronized1;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * 打印object信息
 *
 * @author cunchang
 * @date 2022/3/2 8:26 PM
 */
public class T0_ObjectSize {

    public static void main(String[] args) throws InterruptedException {
        // 1、无锁；未启用偏向锁
//        Object o = new Object();
//        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        // 2、有锁；未启用偏向锁
        // 偏向锁会延迟使用，所以无锁直接升级为轻量级锁
//        Object o = new Object();
//        System.out.println(ClassLayout.parseInstance(o).toPrintable());
//        synchronized (o) {
//            System.out.println(ClassLayout.parseInstance(o).toPrintable());
//        }
        // 3、有锁；启用偏向锁
        // 延迟执行，让jvm启动偏向锁
        TimeUnit.SECONDS.sleep(5);
        Object object = new Object();
        object.notify();
        object.notifyAll();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
        synchronized (object){
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }
    }
}
