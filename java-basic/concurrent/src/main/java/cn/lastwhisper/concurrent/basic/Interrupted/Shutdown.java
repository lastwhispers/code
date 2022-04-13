package cn.lastwhisper.concurrent.basic.Interrupted;

public class Shutdown {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    // 中断的比stop更为强劲。如果在循环体中,出现了类似于wait()方法或者sleep(方法这样的操作，则只能通过中断来识别了。
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Interrupted!");
                        break;
                    }
                    try {
                        // 当线程在waiting、time_waiting时，如果被中断，就会产生InterruptedException
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted When Sleep");
                        // 设置中断状态
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }, "Shutdown");

        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }

}