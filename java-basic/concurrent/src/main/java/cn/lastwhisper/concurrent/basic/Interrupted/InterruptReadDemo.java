package cn.lastwhisper.concurrent.basic.Interrupted;

import java.io.IOException;

public class InterruptReadDemo {
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
    }

    public static void main(String[] args) throws InterruptedException {
        A a = new A();
        a.start();
        Thread.sleep(100);
        System.out.println("main线程对a线程发起中断信号");
        a.interrupt();
    }
}