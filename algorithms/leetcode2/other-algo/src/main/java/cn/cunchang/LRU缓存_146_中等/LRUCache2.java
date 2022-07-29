package cn.cunchang.LRU缓存_146_中等;

import org.junit.Assert;

import java.util.HashMap;
import java.util.LinkedList;

class LRUCache2 {

    private int capacity;

    private LinkedList<Integer> keyCache = new LinkedList<>();

    private HashMap<Integer, Integer> keyAndValueCache = new HashMap<>();

    public LRUCache2(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        final Integer value = keyAndValueCache.get(key);
        if (value == null) {
            return -1;
        }
        keyCache.remove((Object)key);
        keyCache.addLast(key);
        return value;
    }

    public void put(int key, int value) {
        if (keyCache.contains(key)) {
            keyCache.remove((Object)key);
            keyCache.addLast(key);
            keyAndValueCache.put(key, value);
            return;
        }

        if (keyCache.size() >= capacity) {
            Integer k = keyCache.removeFirst();
            keyAndValueCache.remove(k);
        }
        keyCache.addLast(key);
        keyAndValueCache.put(key, value);
    }


    public static void main(String[] args) {
        LRUCache2 lRUCache = new LRUCache2(2);
        lRUCache.put(2, 1);
        lRUCache.put(3, 2);
        Assert.assertEquals(2,lRUCache.get(3));
        Assert.assertEquals(1,lRUCache.get(2));
        lRUCache.put(4, 3);
        Assert.assertEquals(1,lRUCache.get(2));
        Assert.assertEquals(-1,lRUCache.get(3));
        Assert.assertEquals(3,lRUCache.get(4));
    }

}