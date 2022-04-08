package cn.lastwhisper.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ：杨过
 * @date ：Created in 2020/8/16
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description:
 **/
@Slf4j
public class NumbersProducer implements Runnable {
    private BlockingQueue<Integer> numbersQueue;
    private final int poisonPill;
    private final int poisonPillPerProducer;

    public NumbersProducer(BlockingQueue<Integer> numbersQueue, int poisonPill, int poisonPillPerProducer) {
        this.numbersQueue = numbersQueue;
        this.poisonPill = poisonPill;
        this.poisonPillPerProducer = poisonPillPerProducer;
    }
    public void run() {
        try {
            generateNumbers();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void generateNumbers() throws InterruptedException {
        /*for (int i = 0; i < 100; i++) {
            numbersQueue.put(ThreadLocalRandom.current().nextInt(100));
            log.info("潘金莲-{}号,给武大郎的泡药！",Thread.currentThread().getId());
        }*/
        while(true){
            numbersQueue.put(ThreadLocalRandom.current().nextInt(100));
            if(false){break;}
        }

        for (int j = 0; j < poisonPillPerProducer; j++) {
            numbersQueue.put(poisonPill);
            log.info("潘金莲-{}号,往武大郎的药里放入第{}颗毒丸！",Thread.currentThread().getId(),j+1);
        }
    }
}
