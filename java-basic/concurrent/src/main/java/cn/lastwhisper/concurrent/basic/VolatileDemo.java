package cn.lastwhisper.concurrent.basic;

/**
 * @author lastwhisper
 */
public class VolatileDemo implements Runnable {

    private volatile boolean flag = false;

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

    public static void main(String[] args) {
        VolatileDemo runnable = new VolatileDemo();
        new Thread(runnable).start();
        while (true) {
            if (runnable.isFlag()) {
                System.out.println("-----------------");
                break;
            }
        }
    }
}

