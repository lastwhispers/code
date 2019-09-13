package cn.lastwhisper.concurrent.juc.collections;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 跳表
 * @author lastwhisper
 */
public class SkipListMapDemo {

    public static void main(String[] args) {
        ConcurrentSkipListMap<Integer, Integer> skipListMap = new ConcurrentSkipListMap<>();
        for (int i = 0; i < 30; i++) {
            skipListMap.put(i, i);
        }
        for (Map.Entry<Integer, Integer> entry : skipListMap.entrySet()) {
            System.out.println(entry.getKey());
        }
    }

}
