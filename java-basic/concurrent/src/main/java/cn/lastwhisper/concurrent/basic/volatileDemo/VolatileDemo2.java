package cn.lastwhisper.concurrent.basic.volatileDemo;

/**
 * @author lastwhisper
 */
public class VolatileDemo2 {
    static int[] arr = new int[]{1, 2};

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            arr[1] = 1;
        }).start();
        new Thread(() -> {
            while (true) {
                if (arr[1] == 1) {
                    System.out.println("arr[1]==1");
                    break;
                }
            }

        }).start();
    }
}
