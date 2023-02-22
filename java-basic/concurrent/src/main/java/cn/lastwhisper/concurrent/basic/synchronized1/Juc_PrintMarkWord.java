package cn.lastwhisper.concurrent.basic.synchronized1;

import org.openjdk.jol.info.ClassLayout;

/**
 *
 * @author cunchang
 * @date 2022/3/2 8:26 PM
 */
public class Juc_PrintMarkWord {

    public static void main(String[] args) throws InterruptedException {
        // 需要sleep一段时间，因为java对于偏向锁的启动是在启动几秒之后才激活。
        // 因为jvm启动的过程中会有大量的同步块，且这些同步块都有竞争，如果一启动就启动
        // 偏向锁，会出现很多没有必要的锁撤销
        Thread.sleep(5000);
        T t = new T();
        //未出现任何获取锁的时候
        System.out.println(ClassLayout.parseInstance(t).toPrintable());
        synchronized (t){
            // 获取一次锁之后
            System.out.println(ClassLayout.parseInstance(t).toPrintable());
        }
        // 输出hashcode
        System.out.println("hashCode:"+t.hashCode());
        // 计算了hashcode之后，将导致锁的升级
        // 锁已经释放了，输出无锁markword
        System.out.println(ClassLayout.parseInstance(t).toPrintable());
        synchronized (t){
            // 再次获取锁，锁升级，轻量级锁
            System.out.println(ClassLayout.parseInstance(t).toPrintable());
        }
    }
}

class T{
    int i = 0;
}