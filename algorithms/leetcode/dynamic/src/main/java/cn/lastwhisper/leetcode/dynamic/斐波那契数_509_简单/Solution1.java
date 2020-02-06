package cn.lastwhisper.leetcode.dynamic.斐波那契数_509_简单;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/fibonacci-number/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：递归(自顶向下)
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int fib(int N) {
        if (N == 0) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }

        return fib(N - 1) + fib(N - 2);
    }

    public static void main(String[] args) {
        System.out.println(new Solution1().fib(3));
    }
}