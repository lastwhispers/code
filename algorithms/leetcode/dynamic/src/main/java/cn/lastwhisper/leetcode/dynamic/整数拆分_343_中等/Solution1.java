package cn.lastwhisper.leetcode.dynamic.整数拆分_343_中等;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/integer-break/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：树形递归+回溯(28超时)
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    private int max = 0;

    public int integerBreak(int n) {
        dfs(n, n, 1);
        return max;
    }

    private void dfs(int n, int temp, int result) {
        if (temp == 0) {
            max = Math.max(max, result);
        }
        // 至少拆分出1，至多拆分出n-1
        // temp-i >= 0(剪枝)、i < n(剪枝)
        for (int i = 1; temp - i >= 0 && i < n; i++) {
            //if (temp - i < 0) {
            //    break;
            //}
            temp = temp - i;
            result = result * i;
            dfs(n, temp, result);
            temp = temp + i;
            result = result / i;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution1().integerBreak(2));//1
        System.out.println(new Solution1().integerBreak(3));//2
        //System.out.println(new Solution1().integerBreak(10));//36
        //System.out.println(new Solution1().integerBreak(27));//19683
        //System.out.println(new Solution1().integerBreak(28));//26244
    }
}