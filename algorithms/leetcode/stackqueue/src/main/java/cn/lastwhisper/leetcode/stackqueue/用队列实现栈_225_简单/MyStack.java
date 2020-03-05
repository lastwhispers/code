package cn.lastwhisper.leetcode.stackqueue.用队列实现栈_225_简单;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.Queue;

class MyStack {

    // 只入数据
    private Queue<Integer> inQueue = new LinkedList<>();
    // 只出数据
    private Queue<Integer> outQueue = new LinkedList<>();

    /** Initialize your data structure here. */
    public MyStack() {

    }

    /** Push element x onto stack. */
    public void push(int x) {
        inQueue.add(x);

        while (!outQueue.isEmpty()) {
            inQueue.add(outQueue.poll());
        }
        Queue<Integer> temp = outQueue;
        outQueue = inQueue;
        inQueue = temp;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return outQueue.poll();
    }

    /** Get the top element. */
    public int top() {
        return outQueue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return outQueue.isEmpty();
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        Assert.assertEquals(2, myStack.pop());
        Assert.assertFalse(myStack.empty());
        myStack.push(3);
        myStack.push(4);
        Assert.assertEquals(4, myStack.top());
        Assert.assertEquals(4, myStack.pop());
        Assert.assertEquals(3, myStack.pop());
        Assert.assertEquals(1, myStack.pop());
        Assert.assertTrue(myStack.empty());
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */