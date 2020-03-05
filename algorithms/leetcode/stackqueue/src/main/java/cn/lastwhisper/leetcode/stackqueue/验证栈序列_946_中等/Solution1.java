package cn.lastwhisper.leetcode.stackqueue.验证栈序列_946_中等;

import java.util.LinkedList;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/validate-stack-sequences/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        LinkedList<Integer> stack = new LinkedList<>();

        int index = 0;
        for (int item : pushed) {
            stack.push(item);
            while (index < popped.length && !stack.isEmpty() && stack.peek() == popped[index]) {
                stack.pop();
                index++;
            }
        }

        return index == popped.length;
    }

    public static void main(String[] args) {
        //int[] pushed = {1, 2, 3, 4, 5}, popped = {4, 5, 3, 2, 1};
        int[] pushed = {1, 2, 3, 4, 5}, popped = {4, 3, 5, 1, 5};
        System.out.println(new Solution1().validateStackSequences(pushed, popped));
    }
}