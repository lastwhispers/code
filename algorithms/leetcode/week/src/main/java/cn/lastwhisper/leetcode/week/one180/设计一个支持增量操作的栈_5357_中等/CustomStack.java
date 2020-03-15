package cn.lastwhisper.leetcode.week.one180.设计一个支持增量操作的栈_5357_中等;

import org.junit.Assert;

class CustomStack {
    int maxSize;
    int[] arr;
    int top;

    public CustomStack(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        top = -1;
    }

    public void push(int x) {
        if (top + 1 >= maxSize) {
            return;
        }
        arr[++top] = x;
    }

    public int pop() {
        if (top == -1) {
            return -1;
        }
        return arr[top--];
    }

    public void increment(int k, int val) {
        if (k > top) {
            k = top + 1;//
        }
        for (int i = 0; i < k; i++) {
            arr[i] += val;
        }
    }

    public static void main(String[] args) {
        CustomStack customStack = new CustomStack(3); // 栈是空的 []
        customStack.push(1);                          // 栈变为 [1]
        customStack.push(2);                          // 栈变为 [1, 2]
        Assert.assertEquals(2, customStack.pop());                            // 返回 2 --> 返回栈顶值 2，栈变为 [1]
        customStack.push(2);                          // 栈变为 [1, 2]
        customStack.push(3);                          // 栈变为 [1, 2, 3]
        customStack.push(4);                          // 栈仍然是 [1, 2, 3]，不能添加其他元素使栈大小变为 4
        customStack.increment(5, 100);                // 栈变为 [101, 102, 103]
        customStack.increment(2, 100);                // 栈变为 [201, 202, 103]
        Assert.assertEquals(103, customStack.pop());                           // 返回 103 --> 返回栈顶值 103，栈变为 [201, 202]
        Assert.assertEquals(202, customStack.pop());                           // 返回 202 --> 返回栈顶值 202，栈变为 [201]
        Assert.assertEquals(201, customStack.pop());                           // 返回 201 --> 返回栈顶值 201，栈变为 []
        Assert.assertEquals(-1, customStack.pop());                            // 返回 -1 --> 栈为空，返回 -1
    }
}
