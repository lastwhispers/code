package cn.lastwhisper.leetcode.dynamic.不同路径_62_中等;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/fibonacci-number/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：动态规划
     *  每个位置的路径 = 该位置左边的路径 + 该位置上边的路径
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 到达[0,1]、[1,0]只有一步
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    // 每个位置的路径 = 该位置左边的路径 + 该位置上边的路径
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().uniquePaths(7, 3));
    }
}