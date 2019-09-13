package cn.lastwhisper.concurrent.basic;

/**
 * 前置知识：
 *   对象锁：
 *     1）对象锁也叫方法锁，是针对一个对象实例的，它只在该对象的某个内存位置声明一个标识该对象是否拥有锁，所有它只会锁住当前的对象，而并不会对其他对象实例的锁产生任何影响，不同对象访问同一个被synchronized修饰的方法的时候不会阻塞
 *     2）synchronized修饰的非静态方法，即非静态同步方法
 *     3）synchronized(this)，即非静态同步代码块
 *   类锁：
 *     1）synchronized static 修饰的静态方法，即静态同步方法
 *     2）synchronized(类名.class)，即静态同步代码块
 * 题目：判断打印的 1 or 2 or 3？
 * 1. 两个普通同步方法，两个线程，打印? //one  two
 * 2. 新增 Thread.sleep() 给 getOne() ,打印? //one  two
 * 3. 新增普通方法 getThree() , 打印? //three  one   two
 * 4. 两个普通同步方法，两个 Number 对象，打印?  //two  one
 * 5. 修改 getOne() 为静态同步方法，打印?  //two   one
 * 6. 修改两个方法均为静态同步方法，一个 Number 对象?  //one   two
 * 7. 一个静态同步方法，一个非静态同步方法，两个 Number 对象?  //two  one
 * 8. 两个静态同步方法，两个 Number 对象?   //one  two
 * @author lastwhisper
 */
public class TestThread8Monitor {
    public static void main(String[] args) {
        Number number1 = new Number();
        Number number2 = new Number();

        new Thread(new Runnable() {
            @Override
            public void run() {
                number1.getOne();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                number2.getTwo();
            }
        }).start();
    }
}

class Number {
    public static synchronized void getOne(){
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }

    public void getTwo() {
        System.out.println("two");
    }

    public void getThree() {
        System.out.println("three");
    }
}
