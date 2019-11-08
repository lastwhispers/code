package cn.lastwhisper.concurrent.basic.volatileDemo;

public class Main {

    public static void main(String[] args) {
        TestVolatile thread = new TestVolatile();
        thread.start();

        while (true) {
            int a = thread.getA();
            //int a = 5;
            //int b = thread.getB();
            if (thread.isFlag() == 2) {
                System.out.println("flag被改了");
                break;
            }
        }
    }

}

class TestVolatile extends Thread{

    int b = 0;

    volatile int a = 0;

    int[] n = new int[10];

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        n[0] = 2;
        System.out.println("flag:"+isFlag());
    }

    public int isFlag() {
        return n[0];
    }

    public int getA(){
        return a;
    }

    public int getB(){
        return b;
    }
}