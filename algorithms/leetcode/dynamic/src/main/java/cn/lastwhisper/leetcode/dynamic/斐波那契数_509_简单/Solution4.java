package cn.lastwhisper.leetcode.dynamic.斐波那契数_509_简单;

class Solution4 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/fibonacci-number/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：动态规划(自底向上)的另一种写法
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int fib(int N) {
        if (N < 2) return N;
        int item1 = 0;
        int item2 = 1;
        int sum = 0;
        for (int i = 2; i <= N; i++) {
            sum = item1 + item2;
            item1 = item2;
            item2 = sum;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Solution4().fib(2));
        System.out.println(new Solution4().fib(10));
    }
}