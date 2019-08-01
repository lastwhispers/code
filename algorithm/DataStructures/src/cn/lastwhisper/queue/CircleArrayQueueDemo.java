package cn.lastwhisper.queue;

/**
 * 数组实现循环队列
 * @author lastwhisper
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        CircleArrayQueue arrayQueue = new CircleArrayQueue(5);
        arrayQueue.addQueue(100);
        arrayQueue.addQueue(200);
        arrayQueue.addQueue(300);
        arrayQueue.addQueue(400);
        System.out.println("=========显示队列信息========");
        arrayQueue.showQueue();
        System.out.println("=========显示队列头信息========");
        System.out.println(arrayQueue.headQueue());
        System.out.println("=========显示队列长度========");
        System.out.println(arrayQueue.size());
        System.out.println("=========取出队头数据========");
        System.out.println(arrayQueue.getQueue());
        System.out.println(arrayQueue.getQueue());
        System.out.println("=========显示队列长度========");
        System.out.println(arrayQueue.size());
        System.out.println("=========向循环队列添加元素 500,600========");
        arrayQueue.addQueue(500);
        arrayQueue.addQueue(600);
        System.out.println("=========显示队列信息========");
        arrayQueue.showQueue();
    }
}

class CircleArrayQueue {

    private int maxSize; // 队列最大长度
    private int front; // 队头指针
    private int rear;  // 队尾指针
    private int[] arr; // 队列中的数据

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
    }

    // 入队，先添加数据再移动尾指针
    public void addQueue(int data) {
        // 先判断队列是否满了
        if (isFull()) {
            System.out.println("队列已满，不能添加数据");
            return;
        }
        // maxSize=4，rear=3，添加一个元素后rear=0
        rear = (rear + 1) % maxSize;
        arr[rear] = data;
    }

    // 出队，先获取数据再移动头指针
    public int getQueue() {
        // 出队前判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取出数据");
        }
        front = (front + 1) % maxSize;
        int data = arr[front];
        return data;
    }

    // 查看队头元素
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取出数据");
        }
        return arr[front + 1];
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    // 判断队列是否满了
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    // 显示队列情况
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("空队列");
        }
        // 队列中元素个数
        int counter = size();
        // 队头
        int start = front;
        while (counter > 0) {
            start = (start + 1) % maxSize;
            System.out.printf("%d\t", arr[start]);
            counter--;
        }
        System.out.println();
    }

    public int size() {
        return (rear + maxSize - front) % maxSize;
    }
}
