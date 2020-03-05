package cn.lastwhisper.offer.面试题09_用两个栈实现队列_简单;

import java.util.LinkedList;

class CQueue1 {
    // 只存数据
    private LinkedList<Integer> inStack;
    // 只出数据
    private LinkedList<Integer> outStack;

    public CQueue1() {
        inStack = new LinkedList<>();
        outStack = new LinkedList<>();
    }

    /**
     * outStack非空
     *  1、上一次操作是outStack.pop，需要将outStack都入inStack
     *
     *  时间复杂度：O(n)
     *  空间复杂度：O(n)
     */
    public void appendTail(int value) {
        while (!outStack.isEmpty()) {
            inStack.push(outStack.pop());
        }
        inStack.push(value);
    }

    /**
     * 先将inStack都放入outStack，再outStack.pop
     *
     *  时间复杂度：O(n)
     *  空间复杂度：O(n)
     */
    public int deleteHead() {
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
        return !outStack.isEmpty() ? outStack.pop() : -1;
    }

    public static void main(String[] args) {
        CQueue1 cQueue = new CQueue1();
        System.err.println(cQueue.deleteHead());
        cQueue.appendTail(1);
        cQueue.appendTail(2);
        cQueue.appendTail(3);
        System.err.println(cQueue.deleteHead());
        System.err.println(cQueue.deleteHead());
        cQueue.appendTail(4);
        System.err.println(cQueue.deleteHead());
        System.err.println(cQueue.deleteHead());
        System.err.println(cQueue.deleteHead());
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */