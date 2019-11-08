package cn.lastwhisper.concurrent.basic.volatileDemo;

import java.util.concurrent.TimeUnit;

public class VolatileRefExample2 {
    private static Data data = new Data(-1, -1);

    private static class Data {
        private int a;
        private int b;

        public Data(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public synchronized void setValues(int a, int b) {
            this.a = a;
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.b = b;
        }

        public synchronized int[] getValues() {
            return new int[]{a, b};
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 30; i++) {
            int a = i;
            int b = i;

            //writer
            Thread writerThread = new Thread(() -> {data.setValues(a, b);});

            //reader
            Thread readerThread = new Thread(() -> {
                int[] values = data.getValues();
                int x = values[0];
                int y = values[1];
                if (x != y) {
                    System.out.printf("a = %s, b = %s%n", x, y);
                }
            });
            writerThread.start();
            readerThread.start();
            writerThread.join();
            readerThread.join();
        }
        System.out.println("finished");
    }
}