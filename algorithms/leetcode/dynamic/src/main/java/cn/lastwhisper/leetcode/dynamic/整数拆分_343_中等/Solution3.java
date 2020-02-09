package cn.lastwhisper.leetcode.dynamic.整数拆分_343_中等;

class Solution3 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/integer-break/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：树形递归——记忆化搜索
     *  n拆分最大乘积 f(n)=max(1*(n-1),2*(n-2),...(n-1)*1)
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     */
    public int integerBreak(int n) {
        int[] memo = new int[n + 1];
        return dfs(n, memo);
    }   

    private int dfs(int n, int[] memo) {
        if (n == 1) {
            return 1;
        }
        if (memo[n] != 0) {
            return memo[n];
        }
        int result = -1;
        for (int i = 1; i < n; i++) {
            result = Math.max(result, Math.max(i * (n - i), i * dfs(n - i, memo)));
        }
        memo[n] = result;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution3().integerBreak(2));//1
        System.out.println(new Solution3().integerBreak(3));//2
        System.out.println(new Solution3().integerBreak(10));//36
        System.out.println(new Solution3().integerBreak(27));//19683
        System.out.println(new Solution3().integerBreak(28));//26244
    }
}