package cn.lastwhisper.concurrent.basic.Interrupted;

public class InterruptWaitingDemo extends Thread {

    //抛出中断异常，由调用者捕获
    public void interruptibleMethod() throws InterruptedException{
        // ... 包含wait, join 或 sleep 方法
        Thread.sleep(1000);
    }

    // 中断异常，由自己捕获，自己处理
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // 模拟任务代码
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // ... 清理操作
                System.out.println("当前线程执行中，发生中断异常，当前Interrupted："+ isInterrupted());//false
                // 重设中断标志位为true
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("当前线程执行即将结束，当前Interrupted："+isInterrupted());//true
    }

    public static void main(String[] args) {
        InterruptWaitingDemo thread = new InterruptWaitingDemo();
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        thread.interrupt();
    }
}
