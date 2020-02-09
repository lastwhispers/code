package cn.lastwhisper.leetcode.dynamic.整数拆分_343_中等;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/integer-break/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：树形递归
     *  n拆分最大乘积 f(n)=max(1*(n-1),2*(n-2),...(n-1)*1)
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^n)
     * 空间复杂度：O(n)
     */
    public int integerBreak(int n) {
        return dfs(n);
    }

    private int dfs(int n) {
        if (n == 1) {
            return 1;
        }
        int result = -1;
        for (int i = 1; i < n; i++) {
            result = Math.max(result, Math.max(i * (n - i), i * dfs(n - i)));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().integerBreak(2));//1
        System.out.println(new Solution2().integerBreak(3));//2
        System.out.println(new Solution2().integerBreak(10));//36
        System.out.println(new Solution2().integerBreak(27));//19683
        System.out.println(new Solution2().integerBreak(28));//26244
    }
}