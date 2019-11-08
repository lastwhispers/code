package cn.lastwhisper.concurrent.basic.volatileDemo;

/**
 * @author lastwhisper
 */
public class VolatileDemo implements Runnable {

    private volatile boolean[] flag = {false};

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag[0] = true;
        System.out.println("flag=" + flag[0]);
    }

    public boolean isFlag() {
        return flag[0];
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileDemo runnable = new VolatileDemo();
        new Thread(runnable).start();
        while (true) {
            if (runnable.isFlag()) {
                System.out.println("-----------------");
                break;
            }
            //Thread.sleep(10);
        }
    }
}

