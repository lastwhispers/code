package cn.lastwhisper.queue;

/**
 * 数组实现队列
 * @author lastwhisper
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        arrayQueue.addQueue(100);
        arrayQueue.addQueue(200);
        arrayQueue.addQueue(300);
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
        System.out.println("=========显示队列信息========");
        arrayQueue.showQueue();
    }
}

class ArrayQueue {
    private int maxSize; // 队列长度
    private int front; //指向头部
    private int rear; //指向尾部
    private int[] arr; //该数组存放元素

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        this.front = -1;
        this.rear = -1;
    }

    // 入队前判断队列是否满了
    public void addQueue(int data) {
        // 先判断队列是否满了
        if (isFull()) {
            System.out.println("队列已满，不能添加数据");
            return;
        }
        rear++;
        arr[rear] = data;
    }

    // 出队前判断队列是否为空
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取出数据");
        }
        front++;
        return arr[front];
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
        return rear == maxSize - 1;
    }

    // 显示队列情况
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("空队列");
        }
        //for (int i = front + 1; i < rear + 1; i++) {
        //    System.out.printf("%d\t", arr[i]);
        //}
        // 队列中元素个数
        int counter = size();
        // 队头
        int start = front;
        while (counter > 0) {
            start++;
            System.out.printf("%d\t", arr[start]);
            counter--;
        }
        System.out.println();
    }

    public int size() {
        return rear - front;
    }
}