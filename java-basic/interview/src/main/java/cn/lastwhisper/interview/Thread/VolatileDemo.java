package cn.lastwhisper.interview.Thread;

/**
 * 一、volatile:当多个线程进行操作共享数据时，可以保证内存中的数据可见。
 *              相比 synchronize 是一种较为轻量级的同步策略。
 * @author lastwhisper
 */
public class VolatileDemo {
    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();
        while (true) {
            synchronized (td){
                if (td.isFlag()) {
                    System.out.println("-----------------");
                    break;
                }
            }
        }
    }
}

class ThreadDemo implements Runnable {

    private boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("flag=" + flag);
    }

    public boolean isFlag() {
        return flag;
    }
}