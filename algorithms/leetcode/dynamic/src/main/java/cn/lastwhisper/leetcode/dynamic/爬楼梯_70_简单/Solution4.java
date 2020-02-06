package cn.lastwhisper.leetcode.dynamic.爬楼梯_70_简单;

class Solution4 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/climbing-stairs/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：动态规划(自底向上)
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */

    public int climbStairs(int n) {
        if (n < 2) {
            return n;
        }
        int[] arr = new int[n + 1];
        arr[0] = 1;
        arr[1] = 1;

        for (int i = 2; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n];
    }


    public static void main(String[] args) {
        System.out.println(new Solution4().climbStairs(2));//2
        System.out.println(new Solution4().climbStairs(3));//3
        System.out.println(new Solution4().climbStairs(4));//5
    }
}