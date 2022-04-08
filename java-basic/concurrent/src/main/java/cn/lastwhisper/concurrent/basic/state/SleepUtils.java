package cn.lastwhisper.concurrent.basic.state;

import java.util.concurrent.TimeUnit;

public class SleepUtils {
    public static void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}