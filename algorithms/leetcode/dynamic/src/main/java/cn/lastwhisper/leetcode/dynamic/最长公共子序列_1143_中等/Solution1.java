package cn.lastwhisper.leetcode.dynamic.最长公共子序列_1143_中等;

import org.junit.Assert;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/longest-common-subsequence/
     * 题号：1143/最长公共子序列（LCS）
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：暴力搜索
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     */
    public int longestCommonSubsequence(String text1, String text2) {
        return dfs(text1, text2, text1.length() - 1, text2.length() - 1);
    }

    public int dfs(String text1, String text2, int i, int j) {
        if (i == -1 || j == -1) {
            return 0;
        }
        if (text1.charAt(i) == text2.charAt(j)) {
            // 这边找到一个 lcs 的元素，继续往前找
            return dfs(text1, text2, i - 1, j - 1) + 1;
        } else {
            // 谁能让 lcs 最长，就听谁的
            return Math.max(dfs(text1, text2, i - 1, j), dfs(text1, text2, i, j - 1));
        }
    }

    public static void main(String[] args) {
        Assert.assertEquals(3, new Solution1().longestCommonSubsequence("abcde", "ace"));
        Assert.assertEquals(3, new Solution1().longestCommonSubsequence("abc", "abc"));
        Assert.assertEquals(0, new Solution1().longestCommonSubsequence("abc", "def"));
    }
}