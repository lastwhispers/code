package cn.lastwhisper.learn8.util.concurrent.queue.my;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 链表实现的阻塞队列
 * @author lastwhisper
 * @date 2020/4/17
 */
public class LinkedBlockingQueue<E> implements Queue<E> {

    /**
     * 容量
     */
    private final Integer capacity;
    /**
     * 队列的大小，使用 AtomicInteger 来保证其线程安全
     */
    private final AtomicInteger count = new AtomicInteger();
    /** Lock held by take, poll, etc */
    private final ReentrantLock takeLock = new ReentrantLock();
    /** Wait queue for waiting takes */
    private final Condition notEmpty = takeLock.newCondition();
    /** Lock held by put, offer, etc */
    private final ReentrantLock putLock = new ReentrantLock();
    /** Wait queue for waiting puts */
    private final Condition notFull = putLock.newCondition();
    /**
     * 链表头
     */
    private transient Node<E> head;
    /**
     * 链表尾
     */
    private transient Node<E> tail;

    public LinkedBlockingQueue() {
        this(Integer.MAX_VALUE);
    }

    /**
     * 初始化
     *
     * @param capacity 队列容量
     */
    public LinkedBlockingQueue(Integer capacity) {
        // 边界检查
        if (capacity == null || capacity < 0) {
            throw new IllegalArgumentException("capacity is null or less zero");
        }
        this.capacity = capacity;
        head = tail = new Node<>(null);
    }

    /**
     * 实现思路：
     *  1、put元素不为空
     *  2、上put锁
     *  3、队满，put操作阻塞
     *  4、队不满，元素入队
     *  5、校验队列长度是否小于容量，小于则唤醒其他put操作
     *  6、释放put锁
     *  7、校验队列长度是否等于1，等于则唤醒take操作（这个1是刚put的，可能有take在阻塞中）
     * @param item 元素
     * @return boolean
     */
    @Override
    public void put(E item) throws InterruptedException {
        // 1、put元素不为空
        if (item == null) {
            throw new NullPointerException();
        }

        int c = -1;

        final ReentrantLock putLock = this.putLock;
        final Condition notFull = this.notFull;
        final AtomicInteger count = this.count;
        // 2、上put锁
        putLock.lockInterruptibly();
        try {
            // 3、队满，put操作阻塞
            while (count.get() == capacity) {//为什么是while，不是if?防止虚假唤醒
                notFull.await();
            }
            // 4、队不满，元素入队
            enqueue(new Node<>(item));
            // 5、校验队列长度是否小于容量，小于则唤醒其他put操作
            c = count.getAndIncrement();// 旧值
            if (c + 1 < capacity) {
                notFull.signal();
            }
        } finally {
            // 6、释放put锁
            putLock.unlock();
        }
        //  7、校验队列长度是否等于1，等于则唤醒take操作（这个1是刚put的，可能有take在阻塞中）
        if (c == 0) {
            signalNotEmpty();
        }
    }

    /**
     * 实现思路：
     *  1、上take锁
     *  2、队空，take操作阻塞
     *  3、队不空，从对头拿一个元素
     *  4、校验队列长度是否大于0，大于则唤醒其他take操作
     *  5、释放take锁
     *  6、校验队列长度是否等于capacity-1，等于则唤醒put操作（这个-1是刚take的，可能有put在阻塞中）
     * @return 元素
     */
    @Override
    public E take() throws InterruptedException {
        E e;
        int c;

        final ReentrantLock takeLock = this.takeLock;
        final AtomicInteger count = this.count;
        final Condition notEmpty = this.notEmpty;
        // 1、上take锁
        takeLock.lockInterruptibly();
        try {
            // 2、队空，take操作阻塞
            while (count.get() == 0) {//为什么是while，不是if?防止虚假唤醒
                notEmpty.await();
            }
            // 3、队不空，从对头拿一个元素
            e = dequeue();
            c = count.getAndDecrement(); // 次处为旧值，旧值=新值 + 1。为啥是拿旧值？
            // 4、校验队列长度是否大于0，大于则唤醒其他take操作
            if (c > 1) {
                notEmpty.signal();
            }
        } finally {
            // 5、释放take锁
            takeLock.unlock();
        }
        // 6、校验队列长度是否等于capacity-1，等于则唤醒put操作（这个-1是刚take的，可能有put在阻塞中）
        if (c == capacity)
            signalNotFull();
        return e;
    }

    private void signalNotEmpty() {
        final ReentrantLock takeLock = this.takeLock;
        takeLock.lock();
        try {
            notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
    }

    private void signalNotFull() {
        final ReentrantLock putLock = this.putLock;
        putLock.lock();
        try {
            notFull.signal();
        } finally {
            putLock.unlock();
        }
    }

    /**
     * 结点出队（队头）
     *
     */
    private E dequeue() {
        // 取出头节点
        Node<E> h = head;
        // 头节点的下一个节点为 first
        Node<E> first = h.next;
        // 使 h 的下一个节点指向自己
        h.next = h; // help GC
        // 给链表头赋值
        head = first;
        // 取出链表头值
        E x = first.item;
        // 旧头节点指向 null，帮助 GC
        first.item = null;
        // 返回旧头节点值
        return x;
    }

    /**
     * 结点入队（队尾）
     *
     * @param node 结点
     */
    private void enqueue(Node<E> node) {
        tail = tail.next = node;
    }
}
