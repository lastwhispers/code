package cn.lastwhisper.concurrent.basic.Interrupted;

public class InterruptRunnableDemo extends Thread {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            // ... 单次循环代码
            System.out.println("loop...");
        }
        System.out.println("done ");
    }

    /**
     * 如果线程在运行中，interrupt()只是会设置线程的中断标志位，没有任何其它作用。
     * 线程应该在运行过程中合适的位置检查中断标志位，比如说，如果主体代码是一个循环，可以在循环开始处进行检查
     */
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new InterruptRunnableDemo();
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}