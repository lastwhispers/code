package cn.lastwhisper.leetcode.other.LRU缓存机制_146_中等;

import java.util.HashMap;
import java.util.Iterator;

/**
 * @author lastwhisper
 */
public class MyLRUCache {

    private HashMap<Integer, Node> cache;
    private DoubleLinkedList lru;
    private int capacity;

    public MyLRUCache(int capacity) {
        this.cache = new HashMap<>();
        this.lru = new DoubleLinkedList();
        this.capacity = capacity;
    }

    class Node {
        public int key, val;
        public Node prev, next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    /**
     *  1、添加结点到链表头
     *  2、删除某结点
     *  3、获取尾结点
     *  每一步操作都要维护相关结点prev、next指针的关系
     */
    class DoubleLinkedList implements Iterable<Node> {
        private Node head, tail;
        private int size;

        public DoubleLinkedList() {
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

        public void addFirst(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            size++;
        }

        public void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        public Node getLast() {
            if (head == tail.prev) {
                return null;
            }
            return tail.prev;
        }

        public int size() {
            return size;
        }

        @Override
        public Iterator<Node> iterator() {
            return new Itr();
        }
    }

    // 从cache中拿出val,将kv移动到lru首部
    public int get(int key) {
        Node node = cache.get(key);
        if (node != null) {
            lru.remove(node);
            lru.addFirst(node);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int val) {
        // 已经存在，更新cache中的值
        // 将已存在的kv移动到lru首部
        Node node = new Node(key, val);
        if (cache.containsKey(key)) {
            cache.put(key, node);
            lru.remove(node);
            lru.addFirst(node);
            return;
        }
        // 不存在，容量超过限制，移除末尾元素
        if (lru.size() >= capacity) {
            Node lastNode = lru.getLast();
            cache.remove(lastNode.key);
            lru.remove(lastNode);
        }
        // 不存在，正常添加
        cache.put(key, node);
        lru.addFirst(node);
    }

    public static void main(String[] args) {
        MyLRUCache lruCache = new MyLRUCache(3);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);// 3-2-1
        int val = lruCache.get(2);// 2-3-1
        System.out.println("取出" + val);
        lruCache.put(4, 4);// 4-2-3
        lruCache.put(5, 5);// 5-4-2
        DoubleLinkedList lru = lruCache.lru;
        for (Node node : lru) {
            System.out.println(node.key + "\t" + node.val);
        }
    }

}

