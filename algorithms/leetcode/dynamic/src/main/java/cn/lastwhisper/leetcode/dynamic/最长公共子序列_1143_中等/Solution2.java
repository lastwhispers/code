package cn.lastwhisper.leetcode.dynamic.最长公共子序列_1143_中等;

import org.junit.Assert;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/longest-common-subsequence/
     * 题号：1143/最长公共子序列（LCS）
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：动态规划（自顶向下）
     *  一、状态定义：dp[i][j]表示，对于 s1[1..i] 和 s2[1..j]，它们的 LCS 长度是 dp[i][j]。
     *  二、初始状态：
     *  三、转换方程：
     *
     *
     *  https://leetcode-cn.com/problems/longest-common-subsequence/solution/dong-tai-gui-hua-zhi-zui-chang-gong-gong-zi-xu-lie/
     *  https://www.jianshu.com/p/6451410be00a
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length() + 1, n = text2.length() + 1;
        int[][] dp = new int[m][n];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // 这边找到一个 lcs 的元素，继续往前找
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 谁能让 lcs 最长，就听谁的
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        Assert.assertEquals(4, new Solution1().longestCommonSubsequence("google", "elgoog"));
        //Assert.assertEquals(3, new Solution3().longestCommonSubsequence("abcde", "ace"));
        //Assert.assertEquals(3, new Solution3().longestCommonSubsequence("abc", "abc"));
        //Assert.assertEquals(0, new Solution3().longestCommonSubsequence("abc", "def"));
    }
}