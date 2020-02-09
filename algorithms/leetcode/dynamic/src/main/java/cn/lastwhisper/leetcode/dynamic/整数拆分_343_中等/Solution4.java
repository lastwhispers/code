package cn.lastwhisper.leetcode.dynamic.整数拆分_343_中等;

class Solution4 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/integer-break/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：动态规划
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     */
    public int integerBreak(int n) {
        int[] memo = new int[n + 1];
        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                memo[i] = Math.max(memo[i], Math.max(j * (i - j), j * memo[i - j]));
            }
        }
        return memo[n];
    }

    public static void main(String[] args) {
        System.out.println(new Solution4().integerBreak(2));//1
        System.out.println(new Solution4().integerBreak(3));//2
        System.out.println(new Solution4().integerBreak(10));//36
        System.out.println(new Solution4().integerBreak(27));//19683
        System.out.println(new Solution4().integerBreak(28));//26244
    }
}