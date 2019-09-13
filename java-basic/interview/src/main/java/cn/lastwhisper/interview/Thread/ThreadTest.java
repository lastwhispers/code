package cn.lastwhisper.interview.Thread;

/**
 * 线程run方法和start方法的区别
 * @author lastwhisper
 */
public class ThreadTest {
    public static void main(String[] args){
        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("current Thread name is "+Thread.currentThread().getName());
            }
        };
        System.out.println("current Thread name is "+Thread.currentThread().getName());
        //Thread.run();
        thread.start();
    }
}
