package cn.lastwhisper.leetcode.dynamic.爬楼梯_70_简单;

class Solution3 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/climbing-stairs/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：尾递归(自底向上)
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */

    public int climbStairs(int n) {
        return climbStairs(n,0,1);
    }

    private int climbStairs(int n, int rt1, int rt2) {
        return n == 0 ? rt2 : climbStairs(n - 1, rt2, rt1 + rt2);
    }

    public static void main(String[] args) {
        System.out.println(new Solution3().climbStairs(2));//2
        System.out.println(new Solution3().climbStairs(3));//3
        System.out.println(new Solution3().climbStairs(4));//5
    }
}