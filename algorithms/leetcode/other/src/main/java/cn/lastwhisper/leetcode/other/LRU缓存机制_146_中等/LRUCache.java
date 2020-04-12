package cn.lastwhisper.leetcode.other.LRU缓存机制_146_中等;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 146. LRU缓存机制146
 * https://leetcode-cn.com/problems/lru-cache/
 *
 * 需要满足O(1)级别的查找、添加、删除
 * 思路：hash表+双向链表
 *
 */
public class LRUCache {
    // key -> Node(key, val)
    private HashMap<Integer, Node> map;
    // Node(k1, v1) <-> Node(k2, v2)...
    private DoubleList cache;
    // 最大容量
    private int cap;


    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    private static class Node {
        public int key, val;
        public Node next, prev;

        public Node(int k, int v) {
            this.key = k;
            this.val = v;
        }
    }

    private static class DoubleList implements Iterable<Node> {
        private Node head, tail; // 头尾虚节点
        private int size; // 链表元素数

        public DoubleList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        private class Itr implements Iterator<Node> {
            private Node now = head;

            @Override
            public boolean hasNext() {
                return now.next != tail;
            }

            @Override
            public Node next() {
                now = now.next;
                return now;
            }

        }

        // 在链表头部添加节点 node
        public void addFirst(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            size++;
        }

        // 删除链表中的 node 节点（node 一定存在）
        public void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        // 删除链表中最后一个节点，并返回该节点
        public Node removeLast() {
            if (tail.prev == head)
                return null;
            Node last = tail.prev;
            remove(last);
            return last;
        }

        // 返回链表长度
        public int size() {
            return size;
        }

        @Override
        public Iterator<Node> iterator() {
            return new Itr();
        }

    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        int val = map.get(key).val;
        // 利用 put 方法把该数据提前
        put(key, val);
        return val;
    }

    public void put(int key, int val) {
        // 先把新节点 node 做出来
        Node node = new Node(key, val);

        if (map.containsKey(key)) {
            // 删除旧的节点，新的插到头部
            cache.remove(map.get(key));
            cache.addFirst(node);
            // 更新 map 中对应的数据
            map.put(key, node);
        } else {
            if (cap == cache.size()) {
                // 删除链表最后一个数据
                Node last = cache.removeLast();
                map.remove(last.key);
            }
            // 直接添加到头部
            cache.addFirst(node);
            map.put(key, node);
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        lruCache.get(2);
        lruCache.put(4, 4);
        DoubleList list = lruCache.cache;
        for (Node node : list) {
            System.out.println(node.key + "\t" + node.val);
        }
    }
}


