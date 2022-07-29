package cn.cunchang.LRU缓存_146_中等;

import org.junit.Assert;

import java.util.HashMap;

/**
 * @author cunchang
 * @date 2022/7/4 10:28 AM
 */
class LRUCache {

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(2, 1);
        lRUCache.put(3, 2);
        Assert.assertEquals(2, lRUCache.get(3));
        Assert.assertEquals(1, lRUCache.get(2));
        lRUCache.put(4, 3);
        Assert.assertEquals(1, lRUCache.get(2));
        Assert.assertEquals(-1, lRUCache.get(3));
        Assert.assertEquals(3, lRUCache.get(4));
    }

    // 要求"函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。"
    // hashmap存储kv保证 get、put  O(1)、linkedlist存储k保证淘汰队尾
    DoubleLinkedList list;
    HashMap<Integer, Node> kv;
    int capacity;

    /**
     * 以 正整数 作为容量 capacity 初始化 LRU 缓存
     */
    public LRUCache(int capacity) {
        this.capacity = capacity;
        list = new DoubleLinkedList();
        kv = new HashMap<>();
    }

    /**
     * 要求：如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1
     * 实现：
     * 1、直接从map里面get，不存在返回-1
     * 2、存在，将list里面的node节点删除，并加到队头
     */
    public int get(int key) {
        Node node = kv.get(key);
        if (node == null) {
            return -1;
        }
        list.remove(node);
        list.addFirst(node);
        return node.v;
    }

    /**
     * 要求：
     * 1、如果关键字 key 已经存在，则变更其数据值 value ；
     * 2、如果不存在，则向缓存中插入该组 key-value 。
     * 3、如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
     * 实现：
     * 1、k在map中存在，直接覆盖
     * 2、k在map中不存在，
     * - 如果插入关键字数量未超过 capacity，put到map，add到list
     * - 超过了，走淘汰逻辑，移除list队尾的k，同时在map中删除k。然后继续put即可
     */
    public void put(int key, int value) {
        Node node = kv.get(key);
        if (node != null) {
            list.remove(node);
            list.addFirst(node);
            node.v = value;
            return;
        }
        if (kv.size() >= capacity) {
            Node last = list.removeLast();
            kv.remove(last.k);
        }
        node = new Node(key, value);
        kv.put(key, node);
        list.addFirst(node);
    }

    class DoubleLinkedList {
        Node head;
        Node tail;

        public DoubleLinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        // 删除某个节点
        public void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        // node节点插入
        public void addFirst(Node node) {
            node.next = head.next;//构建node.next
            node.prev = head; // 构建node.pre
            head.next.prev = node;// 构建原head.pre
            head.next = node;// 构建head.next
        }
        public Node removeLast() {
            if (tail.prev == head)
                return null;
            Node last = tail.prev;
            remove(last);
            return last;
        }
    }

    static class Node {
        int k, v;
        Node prev, next;

        public Node(int k, int v) {
            this.k = k;
            this.v = v;
        }
    }
}