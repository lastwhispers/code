package cn.lastwhisper.concurrent.basic.Interrupted;

import java.io.IOException;

public class InterruptReadCancelDemo {
    private static class A extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.println("a线程等待输入：");
                    System.out.println(System.in.read());//wait input
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("a线程 exit");
        }

        public void cancel() {
            try {
                System.in.close();
            } catch (IOException e) {
            }
            interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        A t = new A();
        t.start();
        Thread.sleep(100);
        System.out.println("main线程对a线程input进行close");
        t.cancel();
    }
}