package cn.lastwhisper.offer.面试题09_用两个栈实现队列_简单;

import java.util.LinkedList;

class CQueue2 {

    private LinkedList<Integer> inStack;

    private LinkedList<Integer> outStack;

    public CQueue2() {
        inStack = new LinkedList<>();
        outStack = new LinkedList<>();
    }

    /**
     *  时间复杂度：O(1)
     */
    public void appendTail(int value) {
        inStack.push(value);
    }

    /**
     * outStack为空先将inStack都放入outStack，再outStack.pop
     * outStack不为空直接pop
     *  时间复杂度：O(n)
     *  空间复杂度：O(n)
     */
    public int deleteHead() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        return !outStack.isEmpty() ? outStack.pop() : -1;
    }

    public static void main(String[] args) {
        CQueue2 cQueue = new CQueue2();
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