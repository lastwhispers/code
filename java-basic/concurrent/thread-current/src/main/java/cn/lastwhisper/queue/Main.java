package cn.lastwhisper.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author ：杨过
 * @date ：Created in 2020/8/16
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description:
 **/
public class Main {

    public static void main(String[] args) {
        int BOUND = 10;
        int N_PRODUCERS = 16;
        int N_CONSUMERS = Runtime.getRuntime().availableProcessors(); //=8
        int poisonPill = Integer.MAX_VALUE;
        int poisonPillPerProducer = N_CONSUMERS / N_PRODUCERS; // =0
        int mod = N_CONSUMERS % N_PRODUCERS;//0+8=8

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(BOUND);

        for (int i = 1; i < N_PRODUCERS; i++) {
            new Thread(new NumbersProducer(queue, poisonPill, poisonPillPerProducer)).start();
        }

        for (int j = 0; j < N_CONSUMERS; j++) {
            new Thread(new NumbersConsumer(queue, poisonPill)).start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new NumbersProducer(queue, poisonPill, poisonPillPerProducer + mod)).start();
    }

}
