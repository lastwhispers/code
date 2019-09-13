package cn.lastwhisper.interview.Thread;

/**
 * 线程的实现
 * @author lastwhisper
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+"："+i);
        }
    }

    public static void main(String[] args){
        Thread mt1 = new MyThread();
        Thread mt2 = new MyThread();
        Thread mt3 = new MyThread();
        mt1.start();
        mt2.start();
        mt3.start();
    }
}
