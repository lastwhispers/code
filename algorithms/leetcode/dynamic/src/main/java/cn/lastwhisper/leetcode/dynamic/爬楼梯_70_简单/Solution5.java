package cn.lastwhisper.leetcode.dynamic.爬楼梯_70_简单;

class Solution5 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/climbing-stairs/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：动态规划(自底向上)的另一种写法
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int climbStairs(int n) {
        if (n < 2) return n;
        int item1 = 1;
        int item2 = 1;
        int sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = item1 + item2;
            item1 = item2;
            item2 = sum;
        }
        return sum;
    }


    public static void main(String[] args) {
        System.out.println(new Solution5().climbStairs(2));//2
        System.out.println(new Solution5().climbStairs(3));//3
        System.out.println(new Solution5().climbStairs(4));//5
    }
}