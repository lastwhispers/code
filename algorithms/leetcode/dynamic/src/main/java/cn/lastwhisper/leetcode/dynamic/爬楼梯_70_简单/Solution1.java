package cn.lastwhisper.leetcode.dynamic.爬楼梯_70_简单;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/climbing-stairs/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：递归(自顶向下)
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int climbStairs(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(new Solution1().climbStairs(2));
        System.out.println(new Solution1().climbStairs(3));
        System.out.println(new Solution1().climbStairs(4));
    }
}