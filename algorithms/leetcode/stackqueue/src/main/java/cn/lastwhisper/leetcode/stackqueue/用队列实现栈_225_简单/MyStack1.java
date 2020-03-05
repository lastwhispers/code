package cn.lastwhisper.leetcode.stackqueue.用队列实现栈_225_简单;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.Queue;

class MyStack1 {

    Queue<Integer> queue;

    /** Initialize your data structure here. */
    public MyStack1() {
        queue = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.add(x);
        for (int i = 1; i < queue.size(); i++) {
            queue.add(queue.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.poll();
    }

    /** Get the top element. */
    public int top() {
        return queue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.size() == 0;
    }


    public static void main(String[] args) {
        MyStack1 myStack = new MyStack1();
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