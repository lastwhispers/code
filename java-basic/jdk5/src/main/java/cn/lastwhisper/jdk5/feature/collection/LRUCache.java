package cn.lastwhisper.jdk5.feature.collection;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lastwhisper
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private static final int MAX_ENTRIES = 3;

    LRUCache() {
        super(MAX_ENTRIES, 0.75f, true);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > MAX_ENTRIES;
    }

    public static void main(String[] args){
        LRUCache<Integer, String> cache = new LRUCache<>();
        cache.put(1, "a");
        cache.put(2, "b");
        cache.put(3, "c");//1,2,3
        cache.get(1);//2,3,1
        cache.put(4, "d");//3,1,4
        System.out.println(cache.keySet());
    }
}
