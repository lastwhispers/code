package cn.lastwhisper.leetcode.dynamic.三角形最小路径和_120_中等;

import java.util.List;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/triangle/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：动态规划
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() < 1) {
            return 0;
        }
        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }
        int[][] dp = new int[triangle.size()][triangle.size()];
        int result = Integer.MAX_VALUE;
        dp[0][0] = triangle.get(0).get(0);
        dp[1][0] = triangle.get(0).get(0) + triangle.get(1).get(0);
        dp[1][1] = triangle.get(0).get(0) + triangle.get(1).get(1);
        for (int i = 2; i < triangle.size(); i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + triangle.get(i).get(j);
                } else if (j == triangle.get(i).size() - 1) {
                    dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
                }
            }
        }
        for (int num : dp[dp.length - 1]) {
            result = Math.min(result, num);
        }

        return result;
    }

    public static void main(String[] args) {

    }
}