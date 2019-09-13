package cn.lastwhisper.interview.Thread;

/**
 * 线程的实现
 * @author lastwhisper
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+"："+i);
        }
    }
    public static void main(String[] args){
        Thread mr1 = new Thread(new MyRunnable());
        Thread mr2 = new Thread(new MyRunnable());
        Thread mr3 = new Thread(new MyRunnable());
        mr1.start();
        mr2.start();
        mr3.start();
    }
}
