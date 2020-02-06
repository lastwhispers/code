package cn.lastwhisper.leetcode.dynamic.斐波那契数_509_简单;

class Solution5 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/fibonacci-number/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：尾递归(自底向上)
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */

    public int fib(int n) {
        return fib(n, 0, 1);
    }

    // 第n个斐波那契，初始两个值0、1
    private int fib(int n, int rt1, int rt2) {
        return n == 0 ? rt1 : fib(n - 1, rt2, rt1 + rt2);
    }


    public static void main(String[] args) {
        System.out.println(new Solution5().fib(2));
        System.out.println(new Solution5().fib(10));
    }
}