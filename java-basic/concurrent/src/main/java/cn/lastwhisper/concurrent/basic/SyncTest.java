package cn.lastwhisper.concurrent.basic;

public class SyncTest {

    public static int num = 1;

    public static void main(String[] args) {
        SyncTest syncTest = new SyncTest();
        System.out.println(syncTest.counter());
    }

    public synchronized int counter() {
        if (num >= 10) {
            return num;
        }
        System.out.println(num);
        num++;
        return this.counter();
    }

}
