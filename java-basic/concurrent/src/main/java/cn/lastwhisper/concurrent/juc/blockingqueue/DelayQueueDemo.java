package cn.lastwhisper.concurrent.juc.blockingqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author cunchang
 * @date 2022/6/18 11:01 AM
 */
public class DelayQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<Delayed> queue = new DelayQueue<>();
        queue.add(new MyDelay<>(8, "第一次添加任务"));
        queue.add(new MyDelay<>(3, "第二次添加任务"));
        queue.add(new MyDelay<>(5, "第三次添加任务"));

        while (!queue.isEmpty()) {
            Delayed delayed = queue.take();
            System.out.println(delayed);
        }
    }

    static class MyDelay<T> implements Delayed {
        long delayTime; // 延迟时间
        long expire; // 过期时间
        T data;

        public MyDelay(long delayTime, T t) {
            this.delayTime = delayTime;
            // 过期时间 = 当前时间 + 延迟时间
            this.expire = System.currentTimeMillis() + delayTime;
            data = t;
        }

        /**
         * 剩余时间 = 到期时间 - 当前时间
         */
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        /**
         * 优先级规则：两个任务比较，时间短的优先执行
         */
        @Override
        public int compareTo(Delayed o) {
            long f = this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
            return (int) f;
        }


        @Override
        public String toString() {
            return "delayTime=" + delayTime +
                    ", expire=" + expire +
                    ", data=" + data;
        }
    }

}

