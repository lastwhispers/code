package cn.lastwhisper.leetcode.stackqueue.有效的括号_20_简单;

import java.util.LinkedList;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/valid-parentheses/
     * -------------------------------------------------------------------
     * 思考：
     *  1.括号闭合长度肯定是偶数
     *  2.空字符串可被认为是有效字符串
     *  3.左括号必须用相同类型的右括号闭合
     *  4.左括号必须以正确的顺序闭合
     *  5.右括号可以出现在前面
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }

        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                // "})"
                if (stack.isEmpty()) {
                    return false;
                }
                if (!match(stack.pop(), c)) {
                    return false;
                }
            }
        }
        // 两种情况""=true、"(("=false
        return stack.isEmpty();
    }

    /**
     * 左右括号是否配对
     */
    public boolean match(char left, char right) {
        if (left == '(') {
            return right == ')';
        } else if (left == '{') {
            return right == '}';
        } else if (left == '[') {
            return right == ']';
        }
        return false;
    }

    public static void main(String[] args) {
        //String s = "{[]}";
        //String s = "()[]{}";
        //String s = "((";
        //String s = "}}";
        String s = "";
        System.out.println(new Solution1().isValid(s));
    }
}