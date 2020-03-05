package cn.lastwhisper.leetcode.stackqueue.最小栈_155_简单;

import org.junit.Assert;

import java.util.LinkedList;

class MinStack {
    /**
     * 题目地址：https://leetcode-cn.com/problems/min-stack/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    LinkedList<Integer> dataStack = new LinkedList<>();
    LinkedList<Integer> minStack = new LinkedList<>();

    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {
        // 保证minStack栈顶永远是最小值
        if (!minStack.isEmpty()) {
            Integer min = minStack.peek();
            if (min > x) {
                minStack.push(x);
            } else {
                minStack.push(min);
            }
        } else {
            minStack.push(x);
        }
        dataStack.push(x);
    }

    public void pop() {
        dataStack.pop();
        minStack.pop();
    }

    public int top() {
        return dataStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        Assert.assertEquals(-3, minStack.getMin());  // --> 返回 -3.
        minStack.pop();
        Assert.assertEquals(0, minStack.top());    //  --> 返回 0.
        Assert.assertEquals(-2, minStack.getMin());  // --> 返回 -2.
    }
}

