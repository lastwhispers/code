package cn.lastwhisper.learn8.util.concurrent.queue.my;

/**
 * 队列接口
 * @author lastwhisper
 * @date 2020/4/17
 */
public interface Queue<E> {

    // 队列中元素的基本结构
    class Node<E> {
        // 数据本身
        E item;
        // 下一个元素
        Node<E> next;

        // 构造器
        public Node(E item) {
            this.item = item;
        }
    }

    /**
     * 放数据
     * @param item 入参
     */
    void put(E item)  throws InterruptedException;

    /**
     * 拿数据，返回一个泛型值
     * @return 数据
     */
    E take()  throws InterruptedException;

}
