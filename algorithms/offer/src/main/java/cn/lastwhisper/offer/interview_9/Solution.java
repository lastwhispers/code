package cn.lastwhisper.offer.interview_9;

import java.util.Stack;

/**
 * 牛客提交版本
 */
public class Solution {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    
    public void push(int node) {
        stack1.push(node);
    }
    
    public int pop() {
     if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.size() == 0) {
            throw new RuntimeException("stack2为空");
        }
        return stack2.pop();
    }
}